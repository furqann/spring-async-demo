package com.furqan.async.main;

import com.furqan.async.model.Comment;
import com.furqan.async.model.Post;
import com.furqan.async.model.User;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  @Autowired
  MainService service;

  @GetMapping(value = "/without-async", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MainModel> getAll() {

    ResponseEntity<List<Post>> posts = new ResponseEntity<>(HttpStatus.OK);
    ResponseEntity<List<Comment>> comments = new ResponseEntity<>(HttpStatus.OK);
    ResponseEntity<List<User>> users = new ResponseEntity<>(HttpStatus.OK);
    final long startOfCalls = System.currentTimeMillis();
    for (int i = 1; i <= 1; i++) {
      MainControllerHelper mainControllerHelper = new MainControllerHelper().invoke();
      posts = mainControllerHelper.getPosts();
      comments = mainControllerHelper.getComments();
      users = mainControllerHelper.getUsers();
    }

    final long endOfCalls = System.currentTimeMillis();
    final long totalTimeOfExecution = endOfCalls - startOfCalls;
    System.out.println("Total time it took to complete the non-async: " + totalTimeOfExecution);

    return ResponseEntity.ok(new MainModel(comments.getBody(), posts.getBody(), users.getBody()));
  }

  @GetMapping(value = "/with-async", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MainModel> getAllAsync() throws ExecutionException, InterruptedException {
    CompletableFuture<ResponseEntity<List<Comment>>> comments = new CompletableFuture<>();
    CompletableFuture<ResponseEntity<List<User>>> users = new CompletableFuture<>();
    CompletableFuture<ResponseEntity<List<Post>>> posts = new CompletableFuture<>();
    final long startOfCalls = System.currentTimeMillis();

    for (int i = 1; i <= 1; i++) {
      comments = service
          .asyncFetchAllComments();
      users = service
          .asyncFetchAllUsers();
      posts = service
          .asyncFetchAllPosts();
    }
//    CompletableFuture.allOf(comments, users, posts).join();
    final long endOfCalls = System.currentTimeMillis();
    final long totalTimeOfExecution = endOfCalls - startOfCalls;
    System.out.println("Total time it took to complete three async requests: " + totalTimeOfExecution);

    return ResponseEntity
        .ok(new MainModel(comments.get().getBody(), posts.get().getBody(), users.get().getBody()));
  }

  private class MainControllerHelper {

    private ResponseEntity<List<Post>> posts;
    private ResponseEntity<List<Comment>> comments;
    private ResponseEntity<List<User>> users;

    public ResponseEntity<List<Post>> getPosts() {
      return posts;
    }

    public ResponseEntity<List<Comment>> getComments() {
      return comments;
    }

    public ResponseEntity<List<User>> getUsers() {
      return users;
    }

    public MainControllerHelper invoke() {
      posts = service.fetchAllPosts();
      comments = service.fetchAllComments();
      users = service.fetchAllUsers();
      return this;
    }
  }
}
