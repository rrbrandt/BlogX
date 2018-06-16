package com.javasmyths.webblogx;

import com.javasmyths.webblogxcore.model.BlogEntry;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogEntryController extends RootController {

  Logger log = Logger.getLogger(BlogEntryController.class);

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    log.debug("****************************************");
    log.debug("initBinder()");
    log.debug("****************************************");
    log.debug("blog entry root = " + applicationProperties.getBlogEntryRoot());
    blogEntryFileSystem.setApplicationProperties(applicationProperties);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
  }

  @RequestMapping(value = "/blogEntry", method = RequestMethod.GET)
  public ModelAndView blogEntry() {
    log.debug("****************************************");
    log.debug("blogEntry() Request get");
    log.debug("****************************************");

    ModelAndView modelAndView = new ModelAndView("blogEntry", "command", new BlogEntry(new Date()));
    modelAndView.addObject("listOfBlogEntries", generateBlogList());

    return modelAndView;
  }

  @RequestMapping(value = "/blogEntry/{blogEntryDate}", method = RequestMethod.GET)
  public ModelAndView blogEntry(@PathVariable String blogEntryDate, Model model) {
    log.debug("****************************************");
    log.debug("blogEntry() Request get blogEntryDate");
    log.debug("****************************************");
    log.debug("blogEntryDate = " + blogEntryDate);
    BlogEntry blogEntry = blogEntryFileSystem.get(blogEntryDate);
    ModelAndView modelAndView = new ModelAndView("blogEntry", "command", blogEntry);
    modelAndView.addObject("listOfBlogEntries", generateBlogList());

    return modelAndView;
  }

  @RequestMapping(value = "/crudBlogEntry", method = RequestMethod.POST)
  public String addBlogEntry(@RequestParam(required = false, value = "save") String save,
      @RequestParam(required = false, value = "delete") String delete, @ModelAttribute("SpringWeb") BlogEntry blogEntry,
      ModelMap model) {
    log.debug("****************************************");
    log.debug("crudBlogEntry() blogEntry = " + blogEntry);
    log.debug("crudBlogEntry() save = " + save);
    log.debug("crudBlogEntry() delete = " + delete);
    log.debug("****************************************");
    try {
      if ("save".equalsIgnoreCase(save)) {
        model.addAttribute("blogDateType", blogEntry.getBlogEntryDateTime());
        model.addAttribute("blogSubject", blogEntry.getBlogSubject());
        model.addAttribute("blogEntry", blogEntry.getBlogEntry());
        blogEntryFileSystem.save(blogEntry);
        return "result";
      }

      if ("delete".equalsIgnoreCase(delete)) {
        blogEntryFileSystem.delete(blogEntry);
        return "result";
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
      return "FailurePage";
    }
    return "result";
  }
}
