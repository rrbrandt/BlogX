/*
 * Copy Wright @JavaSmyths 2018
 */
package com.javasmyths.webblogxcore.model;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Richard
 */
@Component
@Scope("session")
public class User extends GsonModel implements Serializable {

  private String userId;
  private String password;

  public User() {
    userId = null;
    password = null;
  }

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
