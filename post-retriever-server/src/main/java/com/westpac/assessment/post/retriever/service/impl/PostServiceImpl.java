package com.westpac.assessment.post.retriever.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.westpac.assessment.post.retriever.client.CommentClient;
import com.westpac.assessment.post.retriever.client.PostClient;
import com.westpac.assessment.post.retriever.client.dto.JsonPlaceHolderCommentDTO;
import com.westpac.assessment.post.retriever.client.dto.JsonPlaceHolderPostDTO;
import com.westpac.assessment.post.retriever.dto.EmbeddedDTO;
import com.westpac.assessment.post.retriever.dto.WestpacPostRetrieveResponseDTO;
import com.westpac.assessment.post.retriever.dto.WestpacPostSearchResponseDTO;
import com.westpac.assessment.post.retriever.exception.ResourceNotFoundException;
import com.westpac.assessment.post.retriever.mapper.WespacCommentMapper;
import com.westpac.assessment.post.retriever.mapper.WespacPostMapper;
import com.westpac.assessment.post.retriever.service.PostService;

@Service
public class PostServiceImpl implements PostService {

  private static final String COMMENTS_EMBED_KEY = "comments";

  private static final Logger LOG = LoggerFactory.getLogger(PostServiceImpl.class);

  @Autowired
  private CommentClient commentClient;

  @Autowired
  private PostClient postClient;

  @Override
  public WestpacPostRetrieveResponseDTO getPostByPostId(Long postId, String embed) {
    LOG.info("Beginning to call post retrieving endpoint with the post id='{}'.", postId);
    JsonPlaceHolderPostDTO post = postClient.getPostById(postId);
    if (Objects.isNull(post)) {
      throw new ResourceNotFoundException(String.format("Could not find a post with the given post id='%s'.", postId));
    }
    LOG.debug("Found a post='{}'.", post);
    WestpacPostRetrieveResponseDTO.Builder responseBuilder = new WestpacPostRetrieveResponseDTO
        .Builder()
        .withWestpacPost(WespacPostMapper.convertToDTO(post));

    List<String> embeds = getEmbeds(embed);
    if (!CollectionUtils.isEmpty(embeds)) {
      EmbeddedDTO.Builder embeddedBuilder = new EmbeddedDTO.Builder();
      if (embeds.contains(COMMENTS_EMBED_KEY)) {
        LOG.info("Found 'comments' embed key. Searching comments with the post id='{}'.", postId);
        List<JsonPlaceHolderCommentDTO> associatedComments = commentClient.findCommentsByPostId(post.getId());
        LOG.info("Found '{}' comments for post id='{}'", associatedComments.size(), postId);
        embeddedBuilder.withComments(WespacCommentMapper.convertToDTOs(associatedComments));
      }
      responseBuilder.withEmbedded(embeddedBuilder.build());
    }
    return responseBuilder.build();
  }


  @Override
  public WestpacPostSearchResponseDTO findAll(String embed) {
    LOG.info("Beginning to call find all posts endpoint.");
    List<JsonPlaceHolderPostDTO> posts = postClient.findAll();
    if (CollectionUtils.isEmpty(posts)) {
      LOG.info("Found 0 posts. Returning empty result.");
      return new WestpacPostSearchResponseDTO.Builder().withWestpacPosts(Collections.emptyList()).build();
    }
    LOG.debug("Found {} posts.", posts.size());
    WestpacPostSearchResponseDTO.Builder responseBuilder = new WestpacPostSearchResponseDTO
        .Builder()
        .withWestpacPosts(WespacPostMapper.convertToDTOs(posts));

    List<String> embeds = getEmbeds(embed);
    if (!CollectionUtils.isEmpty(embeds)) {
      EmbeddedDTO.Builder embeddedBuilder = new EmbeddedDTO.Builder();
      if (embeds.contains(COMMENTS_EMBED_KEY)) {
        LOG.info("Found 'comments' embed key.");
        List<JsonPlaceHolderCommentDTO> associatedComments = posts
            .stream()
            .peek(post -> LOG.info("Searching comments with the post id='{}'.", post.getId()))
            .flatMap(post -> commentClient.findCommentsByPostId(post.getId()).stream())
            .collect(Collectors.toList());
        LOG.info("Found '{}' comments.", associatedComments.size());
        embeddedBuilder.withComments(WespacCommentMapper.convertToDTOs(associatedComments));
      }
      responseBuilder.withEmbedded(embeddedBuilder.build());
    }
    return responseBuilder.build();
  }

  @Override
  public WestpacPostSearchResponseDTO searchByUserId(Long userId, String embed) {
    LOG.info("Beginning to call find all posts for a user endpoint with user id='{}'.", userId);
    List<JsonPlaceHolderPostDTO> posts = postClient.findPostsByUserId(userId);
    if (CollectionUtils.isEmpty(posts)) {
      LOG.info("Found 0 posts. Returning empty result.");
      return new WestpacPostSearchResponseDTO.Builder().withWestpacPosts(Collections.emptyList()).build();
    }
    LOG.debug("Found {} posts.", posts.size());
    WestpacPostSearchResponseDTO.Builder responseBuilder = new WestpacPostSearchResponseDTO
        .Builder()
        .withWestpacPosts(WespacPostMapper.convertToDTOs(posts));

    List<String> embeds = getEmbeds(embed);
    if (!CollectionUtils.isEmpty(embeds)) {
      EmbeddedDTO.Builder embeddedBuilder = new EmbeddedDTO.Builder();
      if (embeds.contains(COMMENTS_EMBED_KEY)) {
        LOG.info("Found 'comments' embed key.");
        List<JsonPlaceHolderCommentDTO> associatedComments = posts
            .stream()
            .peek(post -> LOG.info("Searching comments with the post id='{}'.", post.getId()))
            .flatMap(post -> commentClient.findCommentsByPostId(post.getId()).stream())
            .collect(Collectors.toList());
        LOG.info("Found '{}' comments.", associatedComments.size());
        embeddedBuilder.withComments(WespacCommentMapper.convertToDTOs(associatedComments));
      }
      responseBuilder.withEmbedded(embeddedBuilder.build());
    }
    return responseBuilder.build();
  }

  private List<String> getEmbeds(String embedParam) {
    if (StringUtils.isEmpty(embedParam)) {
      LOG.info("No embed param has been passed.");
      return Collections.emptyList();
    }
    LOG.info("Starting to extract embed parameter from the given embed param string, '{}'", embedParam);
    return Stream.of(embedParam.split(","))
        .map(String::trim)
        .map(StringUtils::lowerCase)
        .collect(Collectors.toList());
  }
}
