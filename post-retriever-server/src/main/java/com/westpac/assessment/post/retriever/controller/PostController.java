package com.westpac.assessment.post.retriever.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.westpac.assessment.post.retriever.dto.WestpacPostRetrieveResponseDTO;
import com.westpac.assessment.post.retriever.dto.WestpacPostSearchResponseDTO;
import com.westpac.assessment.post.retriever.exception.BadRequestException;
import com.westpac.assessment.post.retriever.service.PostService;

@RestController
@RequestMapping("posts")
public class PostController {

  private static final Logger LOG = LoggerFactory.getLogger(PostController.class);

  @Autowired
  private PostService service;

  @CrossOrigin
  @RequestMapping(value = "/all", method = GET)
  public ResponseEntity<WestpacPostSearchResponseDTO> findAllWestpacPosts(@RequestParam("embed") Optional<String> embed) {
    LOG.info("Beginning to find all Westpac posts");
    WestpacPostSearchResponseDTO allPosts = service.findAll(embed.orElse(null));
    return new ResponseEntity<WestpacPostSearchResponseDTO>(allPosts, HttpStatus.OK);
  }

  @CrossOrigin
  @RequestMapping(value = "/{id}", method = GET)
  public ResponseEntity<WestpacPostRetrieveResponseDTO> retrieveWestpacPost(@PathVariable("id") Long postId, @RequestParam("embed") Optional<String> embed) {
    if (Objects.isNull(postId)) {
      throw new BadRequestException("The given post id was null.");
    }
    LOG.info("Beginning to find a post with the given post id='{}'", postId);
    WestpacPostRetrieveResponseDTO post = service.getPostByPostId(postId,  embed.orElse(null));
    LOG.info("Found a post with the given post id='{}'", postId);
    return new ResponseEntity<WestpacPostRetrieveResponseDTO>(post, HttpStatus.OK);
  }

  @CrossOrigin
  @RequestMapping(value = "/searches", method = POST)
  public ResponseEntity<WestpacPostSearchResponseDTO> searches(@RequestParam("userId") Long userId, @RequestParam("embed") Optional<String> embed) {
    if (Objects.isNull(userId)) {
      throw new BadRequestException("The given user id was null.");
    }
    LOG.info("Beginning to search posts with the given user id='{}'", userId);
    WestpacPostSearchResponseDTO posts = service.searchByUserId(userId, embed.orElse(null));
    return new ResponseEntity<WestpacPostSearchResponseDTO>(posts, HttpStatus.OK);
  }
}
