/*
 * Copy Wright @JavaSmyths 2018
 */
package com.javasmyths.webblogxcore.model;

/**
 *
 * @author Richard
 */
public class User extends GsonModel {

  private String userId;
  private String password;

  public User(String userId) {
    this.userId = userId;
  }

  public User(String userId, String password) {
    this.userId = userId;
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String UserId) {
    this.userId = UserId;
  }

  @Override
  public String toString() {
    return "User{" + "userId=" + userId + ", password=" + password + '}';
  }

}
