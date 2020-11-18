package org.redcrosswarriors.emailservice;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TestSendVerificationEmail extends SendMail {
    @Test
    void sendVerification() throws Exception {
        String token = "thisisthetoken123";
        String link = "http://localhost:8080/verify/" + token;

        SendVerificationEmail testVerify;
        testVerify = new SendVerificationEmail("testmail@gmail.com", link);
        String testHtml;
        testHtml = String.valueOf(new Scanner(new File("src/main/resources/static/verificationEmailTest.html")).useDelimiter("\\Z").next());
        assertEquals(testVerify.recipients.get(0), "testmail@gmail.com");
        assertEquals(testVerify.getHtmlCode(), testHtml);
    }

}