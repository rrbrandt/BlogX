/*
 * Copyright (C) 2018 JavaSmyths javasmyths@javasmyths.com
 */
package com.javasmyths.webblogxcore.services;

import com.javasmyths.webblogxcore.model.ApplicationProperties;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Richard
 */
public class UserServicesImplTest {
  
  public UserServicesImplTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }

  @Test
  public void testEncryptPassword() {
    System.out.println("encryptPassword");
    UserServicesImpl userServicesImpl = new UserServicesImpl();
    String result = userServicesImpl.encryptPassword("MrSpock");
    assertTrue("Should be true", BCrypt.checkpw("MrSpock", result));
    result = userServicesImpl.encryptPassword("MrSpock");
    assertTrue("Should be true", BCrypt.checkpw("MrSpock", result));
  }
  
}
