package org.redcrosswarriors.emailservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class send_mailTest {

    @Test
    void send_verification() throws Exception{
        send_mail verify;
        verify = new send_mail("lbrombach2@gmail.com", "http://youtube.com/practicalrobotics");
        verify.send_verification();
    }

    @Test
    void send_notification() throws Exception{
        String[] matches = new String[5];
        matches[0] = "kpham1k98@gmail.com";
        matches[1] = "jacobtboesch@gmail.com";
        matches[2] = "lbrombach2@gmail.com";
        matches[3] = "kylejanssen1@gmail.com";
        matches[4] = "tiagijo@gmail.com";

        request req = new request("Vlad Thirsty", "vlad@the_impaler.com", "248-555-1212","I am so thirsty pleeze help. This is a unit test");
        send_mail notify;
        notify = new send_mail(matches, req);
        notify.send_notification();
    }
}