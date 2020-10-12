package com.westpac.assessment.post.retriever.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class ResourceNotFoundException extends HttpStatusCodeException {

  /**
   *
   */
  private static final long serialVersionUID = 1604549590608387651L;

  public ResourceNotFoundException(String statusText) {
    super(HttpStatus.NOT_FOUND, statusText);
  }
}
