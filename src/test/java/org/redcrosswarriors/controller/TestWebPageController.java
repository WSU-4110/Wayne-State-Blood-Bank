package org.redcrosswarriors.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestWebPageController {


    @Test
    void TestGetAccessDenied() {
        WebPageController controller = new WebPageController();
        assertEquals("accessDenied.html", controller.getAccessDenied());
    }

    @Test
    void getMyProfilePage() {
        WebPageController controller = new WebPageController();
        assertEquals("myProfile.html", controller.getMyProfilePage());
    }

    @Test
    void getAddFeedbackPage() {
        WebPageController controller = new WebPageController();
        assertEquals("addFeedback.html", controller.getAddFeedbackPage());
    }

    @Test
    void getViewFeedbackPage() {
        WebPageController controller = new WebPageController();
        assertEquals("viewFeedback.html", controller.getViewFeedbackPage());
    }
}