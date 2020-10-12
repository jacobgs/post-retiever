package com.westpac.assessment.post.retriever.dto;

import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = WestpacPostDTO.Builder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WestpacPostDTO {
  private String userId;
  private Long postId;
  private String postTitle;
  private String postBody;

  // jackson uses the default constructor
  private WestpacPostDTO() {}

  private WestpacPostDTO(Builder builder) {
    userId = builder.userId;
    postId = builder.postId;
    postTitle = builder.postTitle;
    postBody = builder.postBody;
  }


  public String getUserId() {
    return userId;
  }

  public Long getPostId() {
    return postId;
  }

  public String getPostTitle() {
    return postTitle;
  }

  public String getPostBody() {
    return postBody;
  }


  @Override
  public boolean equals(Object obj) {
    if (Objects.isNull(obj)) {
      return false;
    }
    if (!(obj instanceof WestpacPostDTO)) {
      return false;
    }
    WestpacPostDTO other = (WestpacPostDTO) obj;
    return new EqualsBuilder().append(userId, other.userId).append(postId, other.postId)
        .append(postTitle, other.postTitle).append(postBody, other.postBody)
        .build();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(userId).append(postId).append(postTitle).append(postBody).build();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("userId", userId).append("postId", postId).append("postTitle", postTitle).append("postBody", postBody).build();
  }

  public static final class Builder {

    private String userId;
    private Long postId;
    private String postTitle;
    private String postBody;

    public Builder() {}

    // for copying object.
    public Builder(WestpacPostDTO original) {
      if (Objects.nonNull(original)) {
        userId = original.userId;
        postId = original.postId;
        postTitle = original.postTitle;
        postBody = original.postBody;
      }
    }

    public Builder withUserId(String userId) {
      this.userId = userId;
      return this;
    }

    public Builder withPostId(Long postId) {
      this.postId = postId;
      return this;
    }

    public Builder withPostTitle(String postTitle) {
      this.postTitle = postTitle;
      return this;
    }

    public Builder withPostBody(String postBody) {
      this.postBody = postBody;
      return this;
    }

    public WestpacPostDTO build() {
      return new WestpacPostDTO(this);
    }
  }
}
