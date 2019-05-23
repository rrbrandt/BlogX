/*
 * Copyright (C) 2018 JavaSmyths javasmyths@javasmyths.com
 */
package com.javasmyths.webblogxcore.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Richard
 */
public class UserTest {
  
  public UserTest() {
  }
  
  @Test
  public void testConstructors() {
    System.out.println("getPassword");
    User instance = new User("UserId");
    String expResult = "User{userId=UserId, password=null}";
    String result = instance.toString();
    assertEquals(expResult, result);
    
    instance = new User("UserId", "Password");
    assertEquals("User{userId=UserId, password=Password}", instance.toString());
  }

  @Test
  public void testGetPassword() {
    System.out.println("getPassword");
    User instance = new User();
    String expResult = "";
    String result = instance.getPassword();
    assertEquals(null, result);
  }

  @Test
  public void testSetPassword() {
    System.out.println("setPassword");
    String password = "";
    User instance = new User();
    instance.setPassword(password);
  }

  @Test
  public void testGetUserId() {
    System.out.println("getUserId");
    User instance = new User();
    String expResult = "";
    String result = instance.getUserId();
    assertEquals(null, result);
  }

  @Test
  public void testSetUserId() {
    System.out.println("setUserId");
    String UserId = "";
    User instance = new User();
    instance.setUserId(UserId);
  }

  @Test
  public void testToString() {
    System.out.println("toString");
    User instance = new User();
    String expResult = "User{userId=null, password=null}";
    String result = instance.toString();
    assertEquals(expResult, result);
  }
  
}
