package com.javasmyths.webblogxcore.model;

import com.google.gson.Gson;
import java.io.Serializable;

/**
 *
 * @author Richard
 */
public class GsonModel implements Serializable {

  private transient Gson gson = new Gson();

  public Gson getGson() {
    if (gson == null) {
      gson = new Gson();
    }
    return gson;
  }

  public void setGson(Gson gson) {
    this.gson = gson;
  }

  public String toGsonString() {
    return getGson().toJson(this);
  }
}
