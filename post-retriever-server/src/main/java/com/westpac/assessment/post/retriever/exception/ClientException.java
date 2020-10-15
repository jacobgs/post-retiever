package com.westpac.assessment.post.retriever.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class ClientException extends HttpStatusCodeException {

  /**
   *
   */
  private static final long serialVersionUID = -9066009311405877036L;

  public ClientException(String statusText) {
    super(HttpStatus.INTERNAL_SERVER_ERROR, statusText);
  }
}
