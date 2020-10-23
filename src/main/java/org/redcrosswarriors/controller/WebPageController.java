/*
Use this class to load the html file when a user requests a webpage.
 */
package org.redcrosswarriors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebPageController {

    @RequestMapping("/accessDenied")
    public String getAccessDenied(){
        return "accessDenied.html";
    }

}
