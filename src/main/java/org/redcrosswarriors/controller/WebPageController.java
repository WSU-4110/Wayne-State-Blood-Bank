/*
Use this class to load the html file when a user requests a webpage.
 */
package org.redcrosswarriors.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebPageController {

    @RequestMapping("/accessDenied")
    public String getAccessDenied(){
        return "accessDenied.html";
    }

    @RequestMapping("/myProfile")
    @Secured("ROLE_USER")
    public String getMyProfilePage(){
        return "myProfile.html";
    }

    @RequestMapping("/addFeedback")
    @Secured("ROLE_USER")
    public String getAddFeedbackPage(){
        return "addFeedback.html";
    }

    @RequestMapping("/viewFeedback")
    @Secured("ROLE_ADMIN")
    public String getViewFeedbackPage(){
        return "viewFeedback.html";
    }

    @RequestMapping("/viewBloodRequests")
    @Secured("ROLE_USER")
    public String getviewBloodRequestsPage(){
        return "viewBloodRequests.html";
    }

}
