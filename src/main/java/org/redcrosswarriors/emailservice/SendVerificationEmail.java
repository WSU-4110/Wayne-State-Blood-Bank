package org.redcrosswarriors.emailservice;

import java.io.File;
import java.util.Scanner;

public class SendVerificationEmail extends SendMail {
    private final String HTML_TEMPLATE;

    public SendVerificationEmail(String recipient, String URL) throws Exception {
        recipients = new String[1];
        recipients[0] = recipient;
        HTML_TEMPLATE = String.valueOf(new Scanner(new File("src/main/resources/templates/verificationEmail.html")).useDelimiter("\\Z").next());
        htmlCode = HTML_TEMPLATE.replace("URL_to_click", URL);
        subject = "Red Cross Warriors Account Validation";
    }
}
