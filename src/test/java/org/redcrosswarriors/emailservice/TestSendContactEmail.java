package org.redcrosswarriors.emailservice;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TestSendContactEmail extends SendMail {

    @Test
    void sendContact() throws Exception {
        SendContactEmail testContact;
        testContact = new SendContactEmail("test@gmail.com", "This is the test message words for the unit test");
        String testHtml;
        testHtml = String.valueOf(new Scanner(new File("src/main/resources/static/contactUsEmailTest.html")).useDelimiter("\\Z").next());
        assertEquals(testContact.recipients.get(0), "wsuredcrosswarriors@gmail.com");
        assertEquals(testContact.getHtmlCode(), testHtml);
    }
}