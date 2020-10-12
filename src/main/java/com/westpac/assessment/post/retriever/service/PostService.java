package com.westpac.assessment.post.retriever.service;

import com.westpac.assessment.post.retriever.dto.WestpacPostRetrieveResponseDTO;
import com.westpac.assessment.post.retriever.dto.WestpacPostSearchResponseDTO;

public interface PostService {

  WestpacPostRetrieveResponseDTO getPostByPostId(Long postId, String embed);

  WestpacPostSearchResponseDTO findAll(String embed);

  WestpacPostSearchResponseDTO searchByUserId(Long userId, String embed);

}
