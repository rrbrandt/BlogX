package com.javasmyths.webblogxcore.services;

import com.javasmyths.webblogxcore.model.ApplicationProperties;
import com.javasmyths.webblogxcore.model.BlogEntry;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
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
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(formatFileName(blogEntry.getBlogEntryDateTime())))) {
      oos.writeObject(blogEntry);
    } catch (IOException ioex) {
      log.error("Error saving blog entry", ioex);
    }
  }

  @Override
  public List<BlogEntry> get(Date startDate, Date endDate, boolean decending) {
    ArrayList<BlogEntry> blogEntries = new ArrayList<>();
    TreeMap<String, BlogEntry> treeMap = new TreeMap();
    File f = new File(applicationProperties.getBlogEntryRoot());
    File[] list = f.listFiles();

    if (list != null) {
      for (File file : list) {
        String fileName = file.getName();
        if (!file.isDirectory()) {
          String dateString = fileName.substring((fileName.indexOf(".") + 1), fileName.indexOf(".ser"));
          treeMap.put(dateString, getByFormatedDate(dateString));
        }
      }
    }
    blogEntries.addAll(treeMap.subMap(formatDate(startDate), true, formatDate(endDate), true).values());

    return blogEntries;
  }

  @Override
  public BlogEntry get(Date date) {
    BlogEntry blogEntry = readSerializedObj(formatDate(date));
    return blogEntry;
  }

  @Override
  public BlogEntry get(String dateString) {
    BlogEntry blogEntry = readSerializedObj(dateString);
    return blogEntry;
  }

  private BlogEntry getByFormatedDate(String dateString) {
    BlogEntry blogEntry = readSerializedObj(dateString);
    return blogEntry;
  }

  public BlogEntry readSerializedObj(String dateTimeString) {
    BlogEntry blogEntry = null;

    try (
            InputStream file = new FileInputStream(formatFileName(dateTimeString));
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);) {
      blogEntry = (BlogEntry) input.readObject();
    } catch (ClassNotFoundException ex) {
      log.error("Cannot perform input. Class not found.", ex);
    } catch (IOException ex) {
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
  public BlogEntry update(BlogEntry blogEntry) {
    try {
      save(blogEntry);
    } catch (IOException ex) {
      log.error("Could not update blog entry", ex);
    }
    return blogEntry;
  }

  public String formatFileName(Date date) {
    return applicationProperties.getBlogEntryRoot() + "BlogEntry." + formatDate(date) + ".ser";
  }

  public String formatFileName(String dateString) {
    return applicationProperties.getBlogEntryRoot() + "BlogEntry." + dateString + ".ser";
  }

  public static final String formatDate(Date blogEntryDateTime) {
    SimpleDateFormat sm = new SimpleDateFormat("yyyyMMddhhmmssSSS");
    return (sm.format(blogEntryDateTime));
  }

}
