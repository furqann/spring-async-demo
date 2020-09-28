package com.furqan.async.model;

public class User {

  private Integer id;
  private String name;
  private String username;
  private String email;
  private Address address;

  /**
   * No args constructor for use in serialization
   */
  public User() {
  }

  /**
   * @param address
   * @param name
   * @param id
   * @param email
   * @param username
   */
  public User(Integer id, String name, String username, String email, Address address) {
    super();
    this.id = id;
    this.name = name;
    this.username = username;
    this.email = email;
    this.address = address;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

}
