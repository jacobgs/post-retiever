package com.westpac.assessment.post.retriever.mapper;

import java.util.List;
import java.util.stream.Collectors;
import com.westpac.assessment.post.retriever.client.dto.JsonPlaceHolderPostDTO;
import com.westpac.assessment.post.retriever.dto.WestpacPostDTO;

public final class WespacPostMapper {

  private static final WestpacPostDTO.Builder DTO_BUILDER = new WestpacPostDTO.Builder();

  public static List<WestpacPostDTO> convertToDTOs(List<JsonPlaceHolderPostDTO> posts) {
    return posts.parallelStream().map(WespacPostMapper::convertToDTO).collect(Collectors.toList());
  }

  public static WestpacPostDTO convertToDTO(JsonPlaceHolderPostDTO post) {
    return DTO_BUILDER
        .withUserId(post.getUserId())
        .withPostTitle(post.getTitle())
        .withPostId(post.getId())
        .withPostBody(post.getBody())
        .build();
  }
}
