package org.redcrosswarriors.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestWebPageController {


    @Test
    void testGetAccessDenied() {
        WebPageController controller = new WebPageController();
        assertEquals("accessDenied.html", controller.getAccessDenied());
    }

    @Test
    void testGetMyProfilePage() {
        WebPageController controller = new WebPageController();
        assertEquals("myProfile.html", controller.getMyProfilePage());
    }

    @Test
    void testGetAddFeedbackPage() {
        WebPageController controller = new WebPageController();
        assertEquals("addFeedback.html", controller.getAddFeedbackPage());
    }

    @Test
    void testGetViewFeedbackPage() {
        WebPageController controller = new WebPageController();
        assertEquals("viewFeedback.html", controller.getViewFeedbackPage());
    }
}