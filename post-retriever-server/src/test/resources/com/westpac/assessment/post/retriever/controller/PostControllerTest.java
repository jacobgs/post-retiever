package com.westpac.assessment.post.retriever.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.westpac.assessment.post.retriever.dto.WestpacPostDTO;
import com.westpac.assessment.post.retriever.dto.WestpacPostSearchResponseDTO;
import com.westpac.assessment.post.retriever.service.PostService;

@WebMvcTest(PostController.class)
public class PostControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private PostService service;

  @Test
  public void testMissingUserIdParamReturnsBadRequest() throws Exception {
    WestpacPostDTO post = new WestpacPostDTO.Builder().withPostBody("test").withPostId(Long.valueOf(1)).withPostTitle("test").withUserId(Long.valueOf(2)).build();
    WestpacPostSearchResponseDTO response = new WestpacPostSearchResponseDTO.Builder().withWestpacPosts(Collections.singletonList(post)).build();


    given(service.searchByUserId(BDDMockito.any(), BDDMockito.eq(""))).willReturn(response);

    mvc.perform(post("/posts/searches")
        .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isBadRequest());
  }
}
