package com.javasmyths.webblogxcore.services;

import com.javasmyths.webblogxcore.model.BlogEntry;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Richard
 */
public interface BlogEntryPersistance {
  public void save(BlogEntry blogEntry) throws IOException;
  public List<BlogEntry> get(String userId, Date startDate, Date endDate, boolean decending);
  public BlogEntry get(String userId, Date date);
  public BlogEntry get(String userdId, String dateString);
  public boolean delete(BlogEntry blogEntry);
  public BlogEntry update (BlogEntry blogEntry) throws IOException;
}
