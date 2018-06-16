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

}
