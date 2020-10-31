package org.redcrosswarriors.emailservice;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSendNotificationEmail extends SendMail {

    @Test
    void sendNotification() throws Exception {
        final int numMessages = 100;
        String[] matches = new String[numMessages];
        for (int i = 0; i < matches.length; i++) {
            matches[i] = "test" + i + "@fakemail.com";
        }

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