package com.westpac.assessment.post.retriever.dto;

import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = WestpacPostSearchResponseDTO.Builder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WestpacPostSearchResponseDTO {

  private List<WestpacPostDTO> westpacPosts;

  @JsonProperty("_embedded")
  private EmbeddedDTO embedded;

  // jackson uses the default constructor
  private WestpacPostSearchResponseDTO() {}

  private WestpacPostSearchResponseDTO(Builder builder) {
    westpacPosts = builder.westpacPosts;
    embedded = builder.embedded;
  }


  public List<WestpacPostDTO> getWestpacPosts() {
    return westpacPosts;
  }

  public EmbeddedDTO getEmbedded() {
    return embedded;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("westpacPosts", westpacPosts).append("embedded", embedded).build();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(westpacPosts).append(embedded).build();
  }

  @Override
  public boolean equals(Object obj) {
    if (Objects.isNull(obj)) {
      return false;
    }
    if (!(obj instanceof WestpacPostSearchResponseDTO)) {
      return false;
    }
    WestpacPostSearchResponseDTO other = (WestpacPostSearchResponseDTO) obj;
    return new EqualsBuilder().append(westpacPosts, other.westpacPosts).append(embedded, other.embedded).build();
  }

  public static final class Builder {
    private List<WestpacPostDTO> westpacPosts;
    private EmbeddedDTO embedded;

    public Builder() {}

    public Builder(WestpacPostSearchResponseDTO original) {
      if (Objects.nonNull(original)) {
        westpacPosts = original.westpacPosts;
        embedded = original.embedded;
      }
    }

    public Builder withWestpacPosts(List<WestpacPostDTO> westpacPosts) {
      this.westpacPosts = westpacPosts;
      return this;
    }

    public Builder withEmbedded(EmbeddedDTO embedded) {
      this.embedded = embedded;
      return this;
    }

    public WestpacPostSearchResponseDTO build() {
      return new WestpacPostSearchResponseDTO(this);
    }
  }



}
