package com.westpac.assessment.post.retriever.client.dto;

import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class JsonPlaceHolderCommentDTO {

  private Long postId;
  private Long id;
  private String name;
  private String email;
  private String body;

  // jackson uses the default constructor
  private JsonPlaceHolderCommentDTO() {
  }

  private JsonPlaceHolderCommentDTO(Builder builder) {
    postId = builder.postId;
    id = builder.id;
    name = builder.name;
    email = builder.email;
    body = builder.body;
  }

  public Long getPostId() {
    return postId;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getBody() {
    return body;
  }

  @Override
  public boolean equals(Object obj) {
    if (Objects.isNull(obj)) {
      return false;
    }
    if (!(obj instanceof JsonPlaceHolderCommentDTO)) {
      return false;
    }
    JsonPlaceHolderCommentDTO other = (JsonPlaceHolderCommentDTO) obj;
    return new EqualsBuilder().append(postId, other.postId).append(id, other.id)
        .append(name, other.name).append(email, other.email)
        .append(body, other.body).build();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(postId).append(id).append(name).append(email).append(body).build();
  }
  @Override
  public String toString() {
    return new ToStringBuilder(this).append("postId", postId).append("id", id).append("name", name).append("email", email).append("body", body).build();
  }

  public static final class Builder {
    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;

    public Builder() {}

    public Builder(JsonPlaceHolderCommentDTO original) {
      if (Objects.nonNull(original)) {
        postId = original.postId;
        id = original.id;
        name = original.name;
        email = original.email;
        body = original.body;
      }
    }

    public Builder withPostId(Long postId) {
      this.postId = postId;
      return this;
    }

    public Builder withId(Long id) {
      this.id = id;
      return this;
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withEmail(String email) {
      this.email = email;
      return this;
    }

    public Builder withBody(String body) {
      this.body = body;
      return this;
    }

    public JsonPlaceHolderCommentDTO build() {
      return new JsonPlaceHolderCommentDTO(this);
    }
  }



}
