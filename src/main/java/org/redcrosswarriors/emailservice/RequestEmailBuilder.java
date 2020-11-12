package org.redcrosswarriors.emailservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RequestEmailBuilder extends EmailBuilder {
    private Request request;


    RequestEmailBuilder() throws FileNotFoundException {
        subject = "Red Cross Warriors: Someone needs YOUR help!";
    }


    public EmailBuilder setRequest(Request request) throws FileNotFoundException {
        this.request = new Request(request);
        htmlCode = new String(String.valueOf(new Scanner(new File("src/main/resources/templates/matchNotification.html")).useDelimiter("\\Z").next()));
        htmlCode = htmlCode.replace("NAME", request.getName());
        htmlCode = htmlCode.replace("EMAIL_ADDRESS", request.getEmailAddress());
        htmlCode = htmlCode.replace("MESSAGE", request.getMessage());
        htmlCode = htmlCode.replace("PHONE_NUMBER", request.getPhoneNumber());
        return this;
    }

    @Override
    public EmailBuilder setHtmlCode(String htmlCode) throws FileNotFoundException {
        this.htmlCode = htmlCode;
        return this;
    }

    @Override
    public EmailBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }


    @Override
    public EmailBuilder setRecipients(String[] recipients) {
        this.recipients = recipients.clone();
        return this;
    }

}
