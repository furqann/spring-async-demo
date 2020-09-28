package com.furqan.async.main;

import com.furqan.async.model.Comment;
import com.furqan.async.model.Post;
import com.furqan.async.model.User;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class MainServiceImp implements MainService {

  @Autowired
  private RestTemplate restTemplate;
  private final String BASE_PATH = "https://jsonplaceholder.typicode.com/";

  @Override
  public ResponseEntity<List<Post>> fetchAllPosts() {
    try{
      return restTemplate
          .exchange(BASE_PATH + "postss", HttpMethod.GET, null,
              new ParameterizedTypeReference<List<Post>>() {
              });
    }catch (RestClientException ex){
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<List<Comment>> fetchAllComments() {
    return restTemplate
        .exchange(BASE_PATH + "comments", HttpMethod.GET, null,
            new ParameterizedTypeReference<List<Comment>>() {
            });
  }

  @Override
  public ResponseEntity<List<User>> fetchAllUsers() {
    return restTemplate
        .exchange(BASE_PATH + "users", HttpMethod.GET, null,
            new ParameterizedTypeReference<List<User>>() {
            });
  }

  @Override
  @Async
  public CompletableFuture<ResponseEntity<List<Post>>> asyncFetchAllPosts() {
    return CompletableFuture.completedFuture(fetchAllPosts());
  }

  @Override
  @Async
  public CompletableFuture<ResponseEntity<List<Comment>>> asyncFetchAllComments() {
    return CompletableFuture.completedFuture(fetchAllComments());
  }

  @Override
  @Async
  public CompletableFuture<ResponseEntity<List<User>>> asyncFetchAllUsers() {
    return CompletableFuture.completedFuture(fetchAllUsers());
  }
}
