package com.furqan.async.main;

import com.furqan.async.model.Comment;
import com.furqan.async.model.Post;
import com.furqan.async.model.User;
import java.util.List;

public class MainModel {

  private List<Comment> comments;
  private List<Post> posts;
  private List<User> users;

  public MainModel() {
  }

  public MainModel(List<Comment> comments, List<Post> posts,
      List<User> users) {
    this.comments = comments;
    this.posts = posts;
    this.users = users;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }
}
