package com.westpac.assessment.post.retriever.client;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.westpac.assessment.post.retriever.client.dto.JsonPlaceHolderPostDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Component
public class PostClient {

  private final WebClient webClient;

  @Autowired
  public PostClient(@Value("${jsonPlaceHolderServerBaseUrl}") String jsonPlaceHolderServerBaseUrl) {
    webClient =  WebClient.builder()
        .baseUrl(jsonPlaceHolderServerBaseUrl)
        .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
            .tcpConfiguration(client ->
            client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
            .doOnConnected(conn -> conn
                .addHandlerLast(new ReadTimeoutHandler(10))
                .addHandlerLast(new WriteTimeoutHandler(10))))))
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  @CircuitBreaker(name = "jsonPlaceholder")
  @Retry(name = "jsonPlaceholder")
  public List<JsonPlaceHolderPostDTO> findAll() {
    return webClient.get()
        .uri("/posts")
        .retrieve()
        .bodyToFlux(JsonPlaceHolderPostDTO.class).collect(Collectors.toList())
        .block();
  }

  @CircuitBreaker(name = "jsonPlaceholder")
  @Retry(name = "jsonPlaceholder")
  public JsonPlaceHolderPostDTO getPostById(Long postId) {
    return webClient.get()
        .uri(String.format("/posts/%s", postId))
        .retrieve()
        .bodyToMono(JsonPlaceHolderPostDTO.class)
        .block();
  }

  @CircuitBreaker(name = "jsonPlaceholder")
  @Retry(name = "jsonPlaceholder")
  public List<JsonPlaceHolderPostDTO> findPostsByUserId(Long userId) {
    return webClient.get()
        .uri(String.format("/posts?userId=%s", userId))
        .retrieve()
        .bodyToFlux(JsonPlaceHolderPostDTO.class).collect(Collectors.toList())
        .block();
  }
}
