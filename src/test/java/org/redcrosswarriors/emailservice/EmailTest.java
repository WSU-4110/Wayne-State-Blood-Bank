package org.redcrosswarriors.emailservice;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void send_contact() {
        try {
            Email testContact = new ContactEmailBuilder().setHtmlCode("This is the test message words for the unit test").build();
            String testHtml;
            testHtml = String.valueOf(new Scanner(new File("src/main/resources/static/contactUsEmailTest.html")).useDelimiter("\\Z").next());

            assertEquals(testContact.getHtmlCode(), testHtml);

            //uncomment to actually send email
            // testContact.send();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void send_verification() throws Exception {
        String token = "thisisthetoken123";
        String link = "http://localhost:8080/verify/" + token;

        Email testVerify;
        testVerify = new VerificationEmailBuilder().setRecipient("lbrombach2@gmail.com").setHtmlCode(link).build();
        String testHtml;
        testHtml = String.valueOf(new Scanner(new File("src/main/resources/static/verificationEmailTest.html")).useDelimiter("\\Z").next());

        assertEquals(testVerify.getHtmlCode(), testHtml);
    }

    @Test
    void send_notification() throws Exception {
        String[] matches = new String[5];
        matches[0] = "test1@fakemail.com";
        matches[1] = "test2@fakemail.com";
        matches[2] = "test3@fakemail.com";
        matches[3] = "test4@fakemail.com";
        matches[4] = "test5@fakemail.com";

        Request req = new Request("Vlad Thirsty",
                "vlad@the_impaler.com",
                "248-555-1212",
                "I am so thirsty pleeze help. This is a unit test");

        Email testNotify;
        testNotify = new RequestEmailBuilder().setRequest(req).setRecipients(matches).build();
        String testHtml;
        testHtml = String.valueOf(new Scanner(new File("src/main/resources/static/matchNotificationTest.html")).useDelimiter("\\Z").next());

        assertEquals(testNotify.getHtmlCode(), testHtml);

        String[] testRecipients = new String[testNotify.getRecipients().length];
        testRecipients = testNotify.getRecipients();
        for (int i = 0; i < matches.length; i++) {
            assertEquals(matches[i], testRecipients[i]);
        }

    }
}