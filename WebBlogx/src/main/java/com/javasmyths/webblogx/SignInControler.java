package com.javasmyths.webblogx;

import com.javasmyths.webblogxcore.model.BlogEntry;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Richard
 */
@Controller
public class SignInControler extends RootController {
  
    private static final Logger log = LogManager.getLogger();

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    log.debug("****************************************");
    log.debug("initBinder()");
    log.debug("****************************************");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    blogEntryFileSystem.setApplicationProperties(applicationProperties);
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(@ModelAttribute("SpringWeb") BlogEntry blogEntry, @RequestParam("userId") String userId, ModelMap model) {
    log.debug("****************************************");
    log.debug("SignInControler() userId = " + userId);
    log.debug("****************************************");
    model.addAttribute("command", new BlogEntry(new Date()));
    model.addAttribute("listOfBlogEntries", generateBlogList());
    
    return "blogEntry";
  }

}
