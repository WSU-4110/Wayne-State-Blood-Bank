package org.redcrosswarriors.emailservice;

import org.junit.jupiter.api.Test;
import org.redcrosswarriors.model.input.RequestBloodInput;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSendNotificationEmail extends SendMail {

    @Test
    void sendNotification() throws Exception {
        final int NUMBER_OF_MESSAGES = 3;
       // String[] matches = new String[numMessages];
        List<String> matches = new ArrayList<String>();
        for (int i = 0; i < NUMBER_OF_MESSAGES; i++) {
            matches.add("test" + i + "@fakemail.com");
        }
        matches.add("lbrombach2@gmail.com");

        RequestBloodInput requestInput = new RequestBloodInput();
        requestInput.setFirstName("Vlad");
                requestInput.setLastName("Thirsty");
                requestInput.setEmail("vlad@the_impaler.com");
                requestInput.setPhoneNumber("248-555-1212");
                requestInput.setBloodType("A+");
                requestInput.setHospitalName("Beaumont Royal Oak");
                requestInput.setStreet("123 W 13 Mile rd");
                requestInput.setCity("Royal Oak");
                requestInput.setZipCode("48073");
                requestInput.setMessage("I am so thirsty pleeze help. This is a unit test");

        SendNotificationEmail testNotify;
        testNotify = new SendNotificationEmail(matches, requestInput);
        String testHtml;
        testHtml = String.valueOf(new Scanner(new File("src/main/resources/static/matchNotificationTest.html")).useDelimiter("\\Z").next());

      //  assertEquals(testNotify.getHtmlCode(), testHtml);
        System.out.println(testNotify.getHtmlCode());

        for (int i = 0; i < testNotify.getRecipients().length; i++) {
            assertEquals(matches.get(i), testNotify.getRecipients()[i]);
        }

        testNotify.start();
        testNotify.join();

    }

}