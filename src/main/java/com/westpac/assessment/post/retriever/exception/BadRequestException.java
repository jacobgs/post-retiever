package com.westpac.assessment.post.retriever.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class BadRequestException extends HttpStatusCodeException {

  /**
   *
   */
  private static final long serialVersionUID = 1604549590608387651L;

  public BadRequestException(String statusText) {
    super(HttpStatus.BAD_REQUEST, statusText);
  }
}
