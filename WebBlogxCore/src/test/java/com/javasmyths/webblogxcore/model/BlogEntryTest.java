package com.javasmyths.webblogxcore.model;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Richard
 */
public class BlogEntryTest {
  
  public BlogEntryTest() {
  }

  @Test
  public void testGetBlogEntry() {
    System.out.println("****************************************");
    System.out.println("testGetBlogEntry");
    System.out.println("****************************************");
    BlogEntry instance = new BlogEntry();
    String result = instance.getBlogEntry();
    assertEquals(null, result);
  }

  @Test
  public void testSetBlogEntry() {
    System.out.println("****************************************");
    System.out.println("testSetBlogEntry");
    System.out.println("****************************************");
    BlogEntry instance = new BlogEntry();
    BlogEntry result = instance.setBlogEntry("blogEntry");
    assertEquals("blogEntry", result.getBlogEntry());
  }

  @Test
  public void testGetBlogSubject() {
    System.out.println("****************************************");
    System.out.println("testGetBlogSubject");
    System.out.println("****************************************");
    BlogEntry instance = new BlogEntry();
    String result = instance.getBlogSubject();
    assertEquals(null, result);
  }

  @Test
  public void testSetBlogSubject() {
    System.out.println("****************************************");
    System.out.println("testSetBlogSubject");
    System.out.println("****************************************");
    BlogEntry instance = new BlogEntry();
    BlogEntry result = instance.setBlogSubject("blogSubject");
    assertEquals("blogSubject", result.getBlogSubject());
  }

  @Test
  public void testGetBlogEntryDateTime() {
    System.out.println("****************************************");
    System.out.println("testGetBlogEntryDateTime");
    System.out.println("****************************************");
    BlogEntry instance = new BlogEntry();
    Date result = instance.getBlogEntryDateTime();
    assertEquals(null, result);
  }

  @Test
  public void testSetBlogEntryDateTime() {
    System.out.println("****************************************");
    System.out.println("testSetBlogEntryDateTime");
    System.out.println("****************************************");
    Date blogEntryDateTime = new Date();
    BlogEntry instance = new BlogEntry();
    instance.setBlogEntryDateTime(blogEntryDateTime);
    assertEquals(blogEntryDateTime, instance.getBlogEntryDateTime());
  }

  @Test
  public void testToGsonString() {
    System.out.println("****************************************");
    System.out.println("testToGsonString");
    System.out.println("****************************************");
    BlogEntry instance = new BlogEntry();
    instance.setBlogEntry("blogEntry");
    instance.setBlogEntryDateTime(new Date(0));
    instance.setBlogSubject("blogSubject");
    String result = instance.toGsonString();
    assertEquals("{\"blogEntryDateTime\":\"Dec 31, 1969 7:00:00 PM\",\"blogSubject\":\"blogSubject\",\"blogEntry\":\"blogEntry\"}", result);
    
    instance = new BlogEntry();
    instance.setBlogEntry("blogEntry");
    instance.setBlogEntryDateTime(new Date(0));
    instance.setBlogSubject("blogSubject");
    instance.setUser(new User("rrbrandt"));
    result = instance.toGsonString();
    assertEquals("{\"blogEntryDateTime\":\"Dec 31, 1969 7:00:00 PM\",\"blogSubject\":\"blogSubject\",\"blogEntry\":\"blogEntry\",\"user\":{\"userId\":\"rrbrandt\"}}", result);
  }
  
}
