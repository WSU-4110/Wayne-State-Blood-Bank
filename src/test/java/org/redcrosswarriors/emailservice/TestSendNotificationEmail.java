package org.redcrosswarriors.emailservice;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSendNotificationEmail extends SendMail {

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

        SendNotificationEmail testNotify;
        testNotify = new SendNotificationEmail(matches, req);
        String testHtml;
        testHtml = String.valueOf(new Scanner(new File("src/main/resources/static/matchNotificationTest.html")).useDelimiter("\\Z").next());

        assertEquals(testNotify.getHtmlCode(), testHtml);


        for (int i = 0; i < testNotify.getRecipients().length; i++) {
            assertEquals(matches[i], testNotify.getRecipients()[i]);
        }

    }

}