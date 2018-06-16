package com.javasmyths.webblogxcore.model;

import com.google.gson.Gson;

/**
 *
 * @author Richard
 */
public class GsonModel {
  private transient Gson gson = new Gson ();

  public Gson getGson() {
    return gson;
  }

  public void setGson(Gson gson) {
    this.gson = gson;
  }
  
  public String toGsonString () {
    return gson.toJson(this);
  }
}
