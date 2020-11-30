package org.redcrosswarriors.emailservice;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SendContactEmail extends SendMail {

    /**
     * Constructor
     *
     * @param sendersEmail   : The sender's email address
     * @param contactMessage : the body of the message the sender submitted
     * @throws Exception :
     */
    public SendContactEmail(String sendersEmail, String contactMessage) throws Exception {
        recipients = new ArrayList<>();
        recipients.add("wsuredcrosswarriors@gmail.com");
        String HTML_TEMPLATE;
        HTML_TEMPLATE = String.valueOf(new Scanner(new File("src/main/resources/templates/contactEmail.html")).useDelimiter("\\Z").next());
        htmlCode = HTML_TEMPLATE.replace("FROM", sendersEmail);
        htmlCode = htmlCode.replace("MESSAGE", contactMessage);
        subject = "Red Cross Warriors User Contact";
    }


}
