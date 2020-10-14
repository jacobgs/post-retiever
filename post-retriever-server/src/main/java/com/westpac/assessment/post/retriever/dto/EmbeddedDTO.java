package com.westpac.assessment.post.retriever.dto;

import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = EmbeddedDTO.Builder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmbeddedDTO {

  private List<WestpacCommentDTO> comments;

  // jackson uses the default constructor
  private EmbeddedDTO() {}

  private EmbeddedDTO(Builder builder) {
    comments = builder.comments;
  }

  public List<WestpacCommentDTO> getComments() {
    return comments;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("comments", comments).build();
  }

  @Override
  public boolean equals(Object obj) {
    if (Objects.isNull(obj)) {
      return false;
    }
    if (!(obj instanceof EmbeddedDTO)) {
      return false;
    }
    EmbeddedDTO other = (EmbeddedDTO) obj;
    return new EqualsBuilder().append(comments, other.comments).build();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(comments).build();
  }

  public static final class Builder {
    private List<WestpacCommentDTO> comments;

    public Builder() {}

    public Builder(EmbeddedDTO original) {
      if (Objects.nonNull(original)) {
        comments = original.comments;
      }
    }

    public Builder withComments(List<WestpacCommentDTO> comments) {
      this.comments = comments;
      return this;
    }

    public EmbeddedDTO build() {
      return new EmbeddedDTO(this);
    }
  }
}
