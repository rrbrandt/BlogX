package com.javasmyths.webblogxcore.services;

import com.javasmyths.webblogxcore.model.ApplicationProperties;
import com.javasmyths.webblogxcore.model.BlogEntry;
import com.javasmyths.webblogxcore.model.User;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Richard
 */
public class BlogEntryFileSystem implements BlogEntryPersistance {

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
  public void save(BlogEntry blogEntry) throws IOException {
    String pathFile = formatFileName(blogEntry);
    File file = new File(pathFile);
    String justPath = file.getParent();
    File path = new File(justPath);

    if (!path.exists()) {
      path.mkdirs();
      path.setWritable(true);
    }
    log.info("Saving blog entry pathFile = " + pathFile);
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
      oos.writeObject(blogEntry);
    } catch (IOException ioex) {
      log.error("Error saving blog entry", ioex);
      throw ioex;
    }
  }

  @Override
  public List<BlogEntry> get(String userId, Date startDate, Date endDate, boolean decending) {
    ArrayList<BlogEntry> blogEntries = new ArrayList<>();
    TreeMap<String, BlogEntry> treeMap = new TreeMap();
    File f = new File(applicationProperties.getBlogEntryRoot() + userId + "/");
    File[] list = f.listFiles();

    if (list != null) {
      for (File file : list) {
        String fileName = file.getName();
        if (!file.isDirectory()) {
          String dateString = fileName.substring((fileName.indexOf(".") + 1), fileName.indexOf(".ser"));
          treeMap.put(dateString, getByFormatedDate(userId, dateString));
        }
      }
    }
    blogEntries.addAll(treeMap.subMap(formatDate(startDate), true, formatDate(endDate), true).values());

    return blogEntries;
  }

  @Override
  public BlogEntry get(String userId, Date date) {
    BlogEntry blogEntry = readSerializedObj(formatFileName(userId, date));
    return blogEntry;
  }

  @Override
  public BlogEntry get(String userId, String dateString) {
    BlogEntry blogEntry = readSerializedObj(formatFileName(userId, dateString));
    return blogEntry;
  }

  private BlogEntry getByFormatedDate(String userId, String dateTimeString) {
    BlogEntry blogEntry = readSerializedObj(formatFileName(userId, dateTimeString));
    return blogEntry;
  }

  private BlogEntry readSerializedObj(String pathFileName) {
    BlogEntry blogEntry = null;
    try {
      InputStream file = new FileInputStream(pathFileName);
      InputStream buffer = new BufferedInputStream(file);
      ObjectInput input = new ObjectInputStream(buffer);
      blogEntry = (BlogEntry) input.readObject();
    } catch (IOException ex) {
      log.error("Cannot perform input.", ex);
    } catch (ClassNotFoundException ex) {
      log.error("Cannot perform input.", ex);
    }

    return blogEntry;
  }

  @Override
  public boolean delete(BlogEntry blogEntry) {
    File file = new File(formatFileName(blogEntry.getBlogEntryDateTime()));
    return file.delete();
  }

  @Override
  public BlogEntry update(BlogEntry blogEntry) throws IOException {
    try {
      save(blogEntry);
    } catch (IOException ex) {
      log.error("Could not update blog entry", ex);
      throw ex;
    }
    return blogEntry;
  }

  public String formatFileName(BlogEntry blogEntry) {
    return formatFileName(blogEntry.getUserId(), blogEntry.getBlogEntryDateTime());
  }

  public String formatFileName(Date date) {
    return applicationProperties.getBlogEntryRoot() + "BlogEntry." + formatDate(date) + ".ser";
  }

  public String formatFileName(String userId, Date date) {
    return applicationProperties.getBlogEntryRoot() + userId + "/BlogEntry." + formatDate(date) + ".ser";
  }

  public String formatFileName(String userId, String dateString) {
    return applicationProperties.getBlogEntryRoot() + userId + "/BlogEntry." + dateString + ".ser";
  }

  public static final String formatDate(Date blogEntryDateTime) {
    SimpleDateFormat sm = new SimpleDateFormat("yyyyMMddhhmmssSSS");
    return (sm.format(blogEntryDateTime));
  }

}
