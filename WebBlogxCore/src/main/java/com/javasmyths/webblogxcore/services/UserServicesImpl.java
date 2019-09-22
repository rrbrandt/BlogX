/*
 * Copyright (C) 2018 JavaSmyths javasmyths@javasmyths.com
 */
package com.javasmyths.webblogxcore.services;

import com.javasmyths.webblogxcore.model.ApplicationProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Richard
 */
public class UserServicesImpl implements UserServices {

  @Autowired
  ApplicationProperties applicationProperties;
  private static final Logger log = LogManager.getLogger();
// <editor-fold defaultstate="collapsed" desc="getters/setters">

  public ApplicationProperties getApplicationProperties() {
    return applicationProperties;
  }

  public void setApplicationProperties(ApplicationProperties applicationProperties) {
    this.applicationProperties = applicationProperties;
  }
// </editor-fold>  

  @Override
  public String encryptPassword(String clearTextPassword) {
    String encryptedPasswordHash = BCrypt.hashpw(clearTextPassword, BCrypt.gensalt());
    return encryptedPasswordHash;
  }
}
