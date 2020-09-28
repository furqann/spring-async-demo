package com.furqan.async.main;

import com.furqan.async.model.Comment;
import com.furqan.async.model.Post;
import com.furqan.async.model.User;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.http.ResponseEntity;

public interface MainService {

  ResponseEntity<List<Post>> fetchAllPosts();

  ResponseEntity<List<Comment>> fetchAllComments();

  ResponseEntity<List<User>> fetchAllUsers();

  CompletableFuture<ResponseEntity<List<Post>>> asyncFetchAllPosts();

  CompletableFuture<ResponseEntity<List<Comment>>> asyncFetchAllComments();

  CompletableFuture<ResponseEntity<List<User>>> asyncFetchAllUsers();
}
