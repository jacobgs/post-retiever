package com.westpac.assessment.post.retriever.dto;

import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class WestpacCommentDTO {

  private Long postId;
  private Long commentId;
  private String commentName;
  private String userEmailAddress;
  private String commentBody;

  // jackson uses the default constructor
  private WestpacCommentDTO() {
  }

  private WestpacCommentDTO(Builder builder) {
    postId = builder.postId;
    commentId = builder.commentId;
    commentName = builder.commentName;
    userEmailAddress = builder.userEmailAddress;
    commentBody = builder.commentBody;
  }

  public Long getPostId() {
    return postId;
  }

  public Long getCommentId() {
    return commentId;
  }

  public String getCommentName() {
    return commentName;
  }
  public String getUserEmailAddress() {
    return userEmailAddress;
  }
  public String getCommentBody() {
    return commentBody;
  }

  @Override
  public boolean equals(Object obj) {
    if (Objects.isNull(obj)) {
      return false;
    }
    if (!(obj instanceof WestpacCommentDTO)) {
      return false;
    }
    WestpacCommentDTO other = (WestpacCommentDTO) obj;
    return new EqualsBuilder().append(postId, other.postId).append(commentId, other.commentId)
        .append(commentName, other.commentName).append(userEmailAddress, other.userEmailAddress)
        .append(commentBody, other.commentBody).build();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(postId).append(commentId).append(commentName).append(userEmailAddress).append(commentBody).build();
  }
  @Override
  public String toString() {
    return new ToStringBuilder(this).append(postId).append(commentId).append(commentName).append(userEmailAddress).append(commentBody).build();
  }

  public static final class Builder {
    private Long postId;
    private Long commentId;
    private String commentName;
    private String userEmailAddress;
    private String commentBody;

    public Builder() {}

    public Builder(WestpacCommentDTO original) {
      if (Objects.nonNull(original)) {
        postId = original.postId;
        commentId = original.commentId;
        commentName = original.commentName;
        userEmailAddress = original.userEmailAddress;
        commentBody = original.commentBody;
      }
    }

    public Builder withPostId(Long postId) {
      this.postId = postId;
      return this;
    }

    public Builder withCommentId(Long commentId) {
      this.commentId = commentId;
      return this;
    }

    public Builder withCommentName(String commentName) {
      this.commentName = commentName;
      return this;
    }

    public Builder withUserEmailAddress(String userEmailAddress) {
      this.userEmailAddress = userEmailAddress;
      return this;
    }

    public Builder withCommentBody(String commentBody) {
      this.commentBody = commentBody;
      return this;
    }

    public WestpacCommentDTO build() {
      return new WestpacCommentDTO(this);
    }
  }



}
