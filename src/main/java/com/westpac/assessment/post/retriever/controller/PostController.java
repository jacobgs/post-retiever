package com.westpac.assessment.post.retriever.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.westpac.assessment.post.retriever.dto.WestpacPostDTO;

@RestController
@RequestMapping("posts")
public class PostController {

  private static final Logger LOG = LoggerFactory.getLogger(PostController.class);

  @RequestMapping(value = "/all", method = GET)
  public ResponseEntity<List<WestpacPostDTO>> findAllWestpacPosts() {
    LOG.info("Beginning to find all Westpac posts");
    return new ResponseEntity<List<WestpacPostDTO>>(HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = GET)
  public ResponseEntity<WestpacPostDTO> retrieveWestpacPost(@PathVariable("id") Long postId) {
    LOG.info("Beginning to retrieve a post with the given post id='{}'", postId);
    return new ResponseEntity<WestpacPostDTO>(HttpStatus.OK);
  }

  @RequestMapping(value = "/searches", method = POST)
  public ResponseEntity<List<WestpacPostDTO>> searches(@RequestParam("userId") Long userId) {
    LOG.info("Beginning to search posts with the given user id='{}'", userId);
    return new ResponseEntity<List<WestpacPostDTO>>(HttpStatus.OK);
  }
}
