package com.javasmyths.webblogxcore.model;

import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Richard
 */
public class BlogEntry extends GsonModel implements Serializable {

  @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
  private Date blogEntryDateTime;
  private String blogSubject;
  private String blogEntry;

  public BlogEntry() {
  }

  public BlogEntry(Date date) {
    this.blogEntryDateTime = date;
  }

  public BlogEntry(Date blogEntryDateTime, String blogSubject, String blogEntry) {
    this.blogEntryDateTime = blogEntryDateTime;
    this.blogSubject = blogSubject;
    this.blogEntry = blogEntry;
  }

  public String getBlogEntry() {
    return blogEntry;
  }

  public BlogEntry setBlogEntry(String blogEntry) {
    this.blogEntry = blogEntry;
    return this;
  }

  public String getBlogSubject() {
    return blogSubject;
  }

  public BlogEntry setBlogSubject(String blogSubject) {
    this.blogSubject = blogSubject;
    return this;
  }

  public Date getBlogEntryDateTime() {
    return blogEntryDateTime;
  }

  public void setBlogEntryDateTime(Date blogEntryDateTime) {
    this.blogEntryDateTime = blogEntryDateTime;
  }

  @Override
  public String toString() {
    return "BlogEntry{" + "blogEntryDateTime=" + blogEntryDateTime + ", blogSubject=" + blogSubject + ", blogEntry=" + blogEntry + '}';
  }

}
