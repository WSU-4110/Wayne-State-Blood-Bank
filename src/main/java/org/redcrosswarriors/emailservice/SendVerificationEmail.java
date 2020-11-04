package org.redcrosswarriors.emailservice;

import java.io.File;
import java.util.Scanner;

public class SendVerificationEmail extends SendMail {

    /**
     * Constructor
     *
     * @param recipient : the new user's email address
     * @param URL       : the link to the verification page the user should click (must include appended verification token)
     * @throws Exception :
     */
    public SendVerificationEmail(String recipient, String URL) throws Exception {
        recipients = new String[1];
        recipients[0] = recipient;
        String HTML_TEMPLATE;
        HTML_TEMPLATE = String.valueOf(new Scanner(new File("src/main/resources/templates/verificationEmail.html")).useDelimiter("\\Z").next());
        htmlCode = HTML_TEMPLATE.replace("URL_to_click", URL);
        subject = "Red Cross Warriors Account Validation";
    }
}
