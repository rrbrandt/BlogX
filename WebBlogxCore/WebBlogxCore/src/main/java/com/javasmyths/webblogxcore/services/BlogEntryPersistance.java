package com.javasmyths.webblogxcore.services;

import com.javasmyths.webblogxcore.model.BlogEntry;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Richard
 */
public interface BlogEntryPersistance {
  public void save(BlogEntry blogEntry);
  public List<BlogEntry> get(Date startDate, Date endDate, boolean decending);
  public BlogEntry get(Date date);
  public BlogEntry get(String dateString);
  public boolean delete(BlogEntry blogEntry);
  public BlogEntry update (BlogEntry blogEntry);
}
