package com.javasmyths.webblogxcore.model;

import com.google.gson.Gson;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Richard
 */
public class GsonModelTest {
  
  public GsonModelTest() {
  }

  @Test
  public void testGetGson() {
    System.out.println("****************************************");
    System.out.println("getGson");
    System.out.println("****************************************");
    GsonModel instance = new GsonModel();
    Gson result = instance.getGson();
    assertNotNull(instance.getGson());
  }

  @Test
  public void testSetGson() {
    System.out.println("****************************************");
    System.out.println("getGson");
    System.out.println("****************************************");
    Gson gson = new Gson();
    GsonModel instance = new GsonModel();
    instance.setGson(gson);
  }

  @Test
  public void testToGsonString() {
    System.out.println("****************************************");
    System.out.println("getGson");
    System.out.println("****************************************");
    GsonModel instance = new GsonModel();
    String expResult = "";
    String result = instance.toGsonString();
    assertEquals("{}", result);
  }
  
}
