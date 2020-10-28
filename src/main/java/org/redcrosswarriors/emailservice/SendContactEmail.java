package org.redcrosswarriors.emailservice;

import java.io.File;
import java.util.Scanner;

public class SendContactEmail extends SendMail{
    private final String HTML_TEMPLATE;


    SendContactEmail(String sendersEmail, String contactMessage) throws Exception{
        recipients = new String[1];
        recipients[0] = "wsuredcrosswarriors@gmail.com";
        HTML_TEMPLATE = String.valueOf(new Scanner(new File("src/main/resources/templates/contactEmail.html")).useDelimiter("\\Z").next());
        htmlCode = HTML_TEMPLATE.replace("FROM", sendersEmail);
        htmlCode = htmlCode.replace("MESSAGE", contactMessage);
        subject = "Red Cross Warriors User Contact";
    }


}
