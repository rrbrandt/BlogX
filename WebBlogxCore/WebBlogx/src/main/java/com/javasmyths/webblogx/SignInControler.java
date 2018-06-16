package com.javasmyths.webblogx;

import com.javasmyths.webblogxcore.model.BlogEntry;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Richard
 */
@Controller
public class SignInControler extends RootController {

  Logger log = Logger.getLogger(SignInControler.class);

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
  public String login(@ModelAttribute("SpringWeb") BlogEntry blogEntry, ModelMap model) {
    log.debug("****************************************");
    log.debug("SignInControler()");
    log.debug("****************************************");
    model.addAttribute("command", new BlogEntry(new Date()));
    model.addAttribute("listOfBlogEntries", generateBlogList());
    return "blogEntry";
  }

}
