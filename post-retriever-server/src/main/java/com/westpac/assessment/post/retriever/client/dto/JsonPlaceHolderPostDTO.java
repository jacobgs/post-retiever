package com.westpac.assessment.post.retriever.client.dto;

import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class JsonPlaceHolderPostDTO {
  private Long userId;
  private Long id;
  private String title;
  private String body;

  // jackson uses the default constructor
  private JsonPlaceHolderPostDTO() {}

  private JsonPlaceHolderPostDTO(Builder builder) {
    userId = builder.userId;
    id = builder.id;
    title = builder.title;
    body = builder.body;
  }

  public Long getUserId() {
    return userId;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getBody() {
    return body;
  }

  @Override
  public boolean equals(Object obj) {
    if (Objects.isNull(obj)) {
      return false;
    }
    if (!(obj instanceof JsonPlaceHolderPostDTO)) {
      return false;
    }
    JsonPlaceHolderPostDTO other = (JsonPlaceHolderPostDTO) obj;
    return new EqualsBuilder().append(userId, other.userId).append(id, other.id)
        .append(title, other.title).append(body, other.body)
        .build();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(userId).append(id).append(title).append(body).build();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("userId", userId).append("id", id).append("title", title).append("body", body).build();
  }

  public static final class Builder {

    private Long userId;
    private Long id;
    private String title;
    private String body;

    public Builder() {}

    // for copying object.
    public Builder(JsonPlaceHolderPostDTO original) {
      if (Objects.nonNull(original)) {
        userId = original.userId;
        id = original.id;
        title = original.title;
        body = original.body;
      }
    }

    public Builder withUserId(Long userId) {
      this.userId = userId;
      return this;
    }

    public Builder withId(Long id) {
      this.id = id;
      return this;
    }

    public Builder withTitle(String title) {
      this.title = title;
      return this;
    }

    public Builder withBody(String body) {
      this.body = body;
      return this;
    }

    public JsonPlaceHolderPostDTO build() {
      return new JsonPlaceHolderPostDTO(this);
    }
  }
}
