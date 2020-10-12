package com.westpac.assessment.post.retriever.dto;

import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = WestpacPostRetrieveResponseDTO.Builder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WestpacPostRetrieveResponseDTO {

  private WestpacPostDTO westpacPost;

  @JsonProperty("_embedded")
  private EmbeddedDTO embedded;

  // jackson uses the default constructor
  private WestpacPostRetrieveResponseDTO() {}

  private WestpacPostRetrieveResponseDTO(Builder builder) {
    westpacPost = builder.westpacPost;
    embedded = builder.embedded;
  }


  public WestpacPostDTO getWestpacPost() {
    return westpacPost;
  }

  public EmbeddedDTO getEmbedded() {
    return embedded;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("westpacPost", westpacPost).append("embedded", embedded).build();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(westpacPost).append(embedded).build();
  }

  @Override
  public boolean equals(Object obj) {
    if (Objects.isNull(obj)) {
      return false;
    }
    if (!(obj instanceof WestpacPostRetrieveResponseDTO)) {
      return false;
    }
    WestpacPostRetrieveResponseDTO other = (WestpacPostRetrieveResponseDTO) obj;
    return new EqualsBuilder().append(westpacPost, other.westpacPost).append(embedded, other.embedded).build();
  }

  public static final class Builder {
    private WestpacPostDTO westpacPost;
    private EmbeddedDTO embedded;

    public Builder() {}

    public Builder(WestpacPostRetrieveResponseDTO original) {
      if (Objects.nonNull(original)) {
        westpacPost = original.westpacPost;
        embedded = original.embedded;
      }
    }

    public Builder withWestpacPost(WestpacPostDTO westpacPost) {
      this.westpacPost = westpacPost;
      return this;
    }

    public Builder withEmbedded(EmbeddedDTO embedded) {
      this.embedded = embedded;
      return this;
    }

    public WestpacPostRetrieveResponseDTO build() {
      return new WestpacPostRetrieveResponseDTO(this);
    }
  }



}
