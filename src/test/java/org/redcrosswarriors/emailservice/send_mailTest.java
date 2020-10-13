package org.redcrosswarriors.emailservice;

import org.junit.jupiter.api.Test;
import org.redcrosswarriors.model.VerificationToken;

import static org.junit.jupiter.api.Assertions.*;

class send_mailTest {

    @Test
    void send_verification() throws Exception{
        VerificationToken token = new VerificationToken();
        String link = "http://localhost:8080/verify/"+token;
        //String link = "http://localhost:8080";
        send_mail verify;
        verify = new send_mail("lbrombach2@gmail.com", link);
        verify.send_verification();


    }

    @Test
    void send_notification() throws Exception{
        String[] matches = new String[5];
        matches[0] = "1@gmail.com";
        matches[1] = "2@gmail.com";
        matches[2] = "3@gmail.com";
        matches[3] = "4@gmail.com";
        matches[4] = "5@gmail.com";

        request req = new request("Vlad Thirsty",
                "vlad@the_impaler.com",
                "248-555-1212",
                "I am so thirsty pleeze help. This is a unit test");

        send_mail notify;
        notify = new send_mail(matches, req);
        notify.send_notification();
    }
}