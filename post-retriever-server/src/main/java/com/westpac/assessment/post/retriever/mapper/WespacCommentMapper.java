package com.westpac.assessment.post.retriever.mapper;

import java.util.List;
import java.util.stream.Collectors;
import com.westpac.assessment.post.retriever.client.dto.JsonPlaceHolderCommentDTO;
import com.westpac.assessment.post.retriever.dto.WestpacCommentDTO;

public class WespacCommentMapper {
  private static final WestpacCommentDTO.Builder DTO_BUILDER = new WestpacCommentDTO.Builder();

  public static List<WestpacCommentDTO> convertToDTOs(List<JsonPlaceHolderCommentDTO> comments) {
    return comments.parallelStream().map(WespacCommentMapper::convertToDTO).collect(Collectors.toList());
  }

  public static WestpacCommentDTO convertToDTO(JsonPlaceHolderCommentDTO comment) {
    return DTO_BUILDER
        .withCommentBody(comment.getBody())
        .withCommentId(comment.getId())
        .withCommentName(comment.getName())
        .withPostId(comment.getPostId())
        .withUserEmailAddress(comment.getEmail())
        .build();
  }
}
