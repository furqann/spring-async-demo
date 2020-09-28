package com.furqan.async.model;

public class Geo {

  private String lat;
  private String lng;

  /**
   * No args constructor for use in serialization
   */
  public Geo() {
  }

  /**
   * @param lng
   * @param lat
   */
  public Geo(String lat, String lng) {
    super();
    this.lat = lat;
    this.lng = lng;
  }

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public String getLng() {
    return lng;
  }

  public void setLng(String lng) {
    this.lng = lng;
  }

}
