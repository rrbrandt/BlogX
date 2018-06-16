package com.javasmyths.webblogx;

import com.javasmyths.webblogxcore.model.ApplicationProperties;
import com.javasmyths.webblogxcore.model.BlogEntry;
import com.javasmyths.webblogxcore.services.BlogEntryFileSystem;
import static com.javasmyths.webblogxcore.services.BlogEntryFileSystem.formatDate;
import static j2html.TagCreator.a;
import static j2html.TagCreator.li;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Richard
 */
public class RootController {

  @Autowired
  public ApplicationProperties applicationProperties;
  public BlogEntryFileSystem blogEntryFileSystem = new BlogEntryFileSystem();

  public String generateBlogList() {
    StringBuilder stringBuilder = new StringBuilder("<ul>\n");
    List<BlogEntry> blogEntriesList = blogEntryFileSystem.get(new Date(0), new Date());

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    for (BlogEntry blogEntryNew : blogEntriesList) {
      Date blogEntryDate = blogEntryNew.getBlogEntryDateTime();
      String blogEntryList = li().with(a().attr("href", "/WebBlogx/blogEntry/" + formatDate(blogEntryDate)).withText(dateFormat.format(blogEntryDate))).render();
      stringBuilder.append("  ").append(blogEntryList).append("\n");
    }
    stringBuilder.append("</ul>\n");

    return stringBuilder.toString();
  }

}
