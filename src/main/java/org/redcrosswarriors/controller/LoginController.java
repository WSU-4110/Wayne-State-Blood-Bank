package org.redcrosswarriors.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    // Login form
    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }

    // remove this later after team is familiar with rest api process
    @RequestMapping(value="/comments")
    @Secured("ROLE_USER")
    public String getCommentsPage(){
        return "message.html";
    }

    @RequestMapping("/accessDenied")
    public String getAccessDenied(){
        return "accessDenied.html";
    }

}
