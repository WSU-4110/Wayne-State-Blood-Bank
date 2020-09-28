/*
Use this class to load the html file when a user requests a webpage.
 */
package org.redcrosswarriors.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebPageController {

    // remove this later after team is familiar with rest api process
    @RequestMapping(value="/comments")
    @Secured("ROLE_USER")
    public String getCommentsPage(){
        return "message.html";
    }

}
