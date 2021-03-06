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


    @RequestMapping("/viewRequestBlood")
    @Secured("ROLE_USER")
    public String viewResults(){
        return "viewRequestBlood.html";
    }

    @RequestMapping("/bloodRequest")
    @Secured("ROLE_USER")
    public String requestBlood(){
        return "bloodRequest.html";
    }

    @RequestMapping("/addBloodDrive")
    @Secured("ROLE_ADMIN")
    public String getAddBloodDrivePage(){
        return "addBloodDrive.html";
    }

    @RequestMapping("/addNewsAndEvents")
    @Secured("ROLE_ADMIN")
    public String getAddNewsAndEventsPage(){
        return "addNewsEvents.html";
    }
    @RequestMapping("/manageUsers")
    @Secured("ROLE_ADMIN")
    public String getUsers() { return "manageUsers.html"; }

}
