package org.redcrosswarriors.emailservice;

import java.io.File;
import java.util.Scanner;

public class SendNotificationEmail extends SendMail {

    /**
     * Constructor
     *
     * @param _recipients : array of email addresses of matches to be notified
     * @param request     : Request object. Elements of this are inserted to html email tamplate and sent to recipients
     * @throws Exception :
     */
    public SendNotificationEmail(String[] _recipients, Request request) throws Exception {
        recipients = new String[_recipients.length];
        System.arraycopy(_recipients, 0, recipients, 0, _recipients.length);

        subject = "YOUR Blood is Needed!";
        String HTML_TEMPLATE;
        HTML_TEMPLATE = String.valueOf(new Scanner(new File("src/main/resources/templates/matchNotification.html")).useDelimiter("\\Z").next());
        htmlCode = HTML_TEMPLATE.replace("NAME", request.getName());
        htmlCode = htmlCode.replace("EMAIL_ADDRESS", request.getEmailAddress());
        htmlCode = htmlCode.replace("MESSAGE", request.getMessage());
        htmlCode = htmlCode.replace("PHONE_NUMBER", request.getPhoneNumber());
    }
}
