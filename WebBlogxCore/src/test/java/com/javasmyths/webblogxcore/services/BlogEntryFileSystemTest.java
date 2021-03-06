package com.javasmyths.webblogxcore.services;

import com.javasmyths.webblogxcore.model.ApplicationProperties;
import com.javasmyths.webblogxcore.model.BlogEntry;
import com.javasmyths.webblogxcore.model.User;
import java.io.File;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Richard
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        {
          "classpath:applicationContext.xml"
        }
)
public class BlogEntryFileSystemTest {

  @Autowired
  ApplicationProperties applicationProperties;

  public BlogEntryFileSystemTest() {
  }

  @Test
  public void testSaveReadUpdateDelete() {
    System.out.println("****************************************");
    System.out.println("testSaveReadUpdateDelete");
    System.out.println("****************************************");

    try {
      BlogEntry blogEntry = new BlogEntry();
      blogEntry.setBlogEntryDateTime(new Date());
      blogEntry.setBlogSubject("blogsubject");
      blogEntry.setBlogEntry("blogEntry");
      blogEntry.setUserId("UserId");

      BlogEntryFileSystem blogEntryFileSystem = new BlogEntryFileSystem();
      blogEntryFileSystem.setApplicationProperties(applicationProperties);
      blogEntryFileSystem.save(blogEntry);

      BlogEntry blogEntryReadback = blogEntryFileSystem.get(blogEntry.getUserId(), blogEntry.getBlogEntryDateTime());
      assertEquals("Record read back should be equal", blogEntry.toString(), blogEntryReadback.toString());
      assertEquals("Record read back should be equal", blogEntry.toGsonString(), blogEntryReadback.toGsonString());
      blogEntry.setBlogEntry("blogEntryUpdated");
      blogEntry.setBlogSubject("blogSubject");
      blogEntryFileSystem.update(blogEntry);
      blogEntryReadback = blogEntryFileSystem.get(blogEntry.getUserId(), blogEntry.getBlogEntryDateTime());
      assertEquals("Record read back should be equal", blogEntry.toGsonString(), blogEntryReadback.toGsonString());
      blogEntryFileSystem.delete(blogEntry);
      assertTrue("An exception was not thrown", true);

      Date startDate = new Date(0);
      Date endDate = new Date();
      List<BlogEntry> result = blogEntryFileSystem.get("UserId", startDate, endDate, false);
      assertTrue("Greater then zero", result.size() > 0);

      BlogEntry blogEntryReadSingle = blogEntryFileSystem.get("UserId", BlogEntryFileSystem.formatDate(result.get(0).getBlogEntryDateTime()));
      assertNotNull("Greater then zero", blogEntryReadSingle);
      
    } catch (Exception ex) {
      ex.printStackTrace(System.out);
      assertFalse("An exception should not be thrown", true);

    }
  }

  @Test
  public void testGetBlack() {
    System.out.println("****************************************");
    System.out.println("testGetBlack");
    System.out.println("****************************************");
    
    try {
      BlogEntryFileSystem blogEntryFileSystem = new BlogEntryFileSystem();
      blogEntryFileSystem.setApplicationProperties(applicationProperties);

      BlogEntry blogEntryReadBack = blogEntryFileSystem.get("goobegoo", new Date(0));
      assertNull("Should be null", blogEntryReadBack);

    } catch (Exception ex) {
      ex.printStackTrace(System.out);
      assertFalse("An exception should not be thrown", true);
    }
  }
  
  @Test
  public void testUpdateBlack() {
    System.out.println("****************************************");
    System.out.println("testSaveReadUpdateDelete");
    System.out.println("****************************************");

    try {
      BlogEntry blogEntry = new BlogEntry();
      blogEntry.setBlogEntryDateTime(new Date());
      blogEntry.setBlogSubject("blogsubject");
      blogEntry.setBlogEntry("blogEntry");
      blogEntry.setUserId("UserId");

      BlogEntryFileSystem blogEntryFileSystem = new BlogEntryFileSystem();
      blogEntryFileSystem.setApplicationProperties(applicationProperties);
      blogEntryFileSystem.save(blogEntry);

      String filePath = blogEntryFileSystem.formatFileName(blogEntry);
      File file = new File(filePath);
      file.setReadOnly();
      try {
        BlogEntry updatedBlogEntry = blogEntryFileSystem.update(blogEntry);
        assertFalse("An exception should have been thrown", true);
      } catch (Exception ex) {
        assertTrue("An exception should be thrown", true);
      }
      file.setWritable(true);

    } catch (Exception ex) {
      ex.printStackTrace(System.out);
      assertFalse("An exception should not be thrown", true);

    }
  }

  @Test
  public void testGet_Date_Date() {
    System.out.println("****************************************");
    System.out.println("testGet_Date_Date");
    System.out.println("****************************************");
    try {
      Date startDate = new Date(0);
      Date endDate = new Date();
      BlogEntryFileSystem blogEntryFileSystem = new BlogEntryFileSystem();
      blogEntryFileSystem.setApplicationProperties(applicationProperties);
      FileUtils.cleanDirectory(new File(applicationProperties.getBlogEntryRoot()));
      List<BlogEntry> result = blogEntryFileSystem.get("UserId", startDate, endDate, false);
      assertTrue("Greater then zero", result.size() == 0);
    } catch (Exception ex) {
      ex.printStackTrace(System.out);
      assertFalse("An exception should not be thrown", true);

    }
  }

  @Test
  public void testFormatDate() {
    System.out.println("****************************************");
    System.out.println("testFormatDate");
    System.out.println("****************************************");
    Date blogEntryDateTime = new Date(0);
    BlogEntryFileSystem blogEntryFileSystem = new BlogEntryFileSystem();
    assertEquals("19691231070000000", BlogEntryFileSystem.formatDate(blogEntryDateTime));
    blogEntryDateTime = new Date(-10000000000000l);
    blogEntryFileSystem = new BlogEntryFileSystem();
    assertEquals("16530210011320000", BlogEntryFileSystem.formatDate(blogEntryDateTime));
  }

  @Test
  public void testFormatFileName() {
    System.out.println("****************************************");
    System.out.println("testFormatDate");
    System.out.println("****************************************");
    Date blogEntryDateTime = new Date(0);
    BlogEntryFileSystem blogEntryFileSystem = new BlogEntryFileSystem();
    blogEntryFileSystem.setApplicationProperties(applicationProperties);
    assertEquals("c:/opt/javasmyths/blogEntries/test/userId/BlogEntry.19691231070000000.ser", blogEntryFileSystem.formatFileName("userId", blogEntryDateTime));

  }
}
