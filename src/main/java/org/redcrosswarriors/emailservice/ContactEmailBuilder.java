package org.redcrosswarriors.emailservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ContactEmailBuilder extends EmailBuilder {


    ContactEmailBuilder() {
        subject = "Red Cross Warriors User Contact";
        recipients = new String[1];
        recipients[0] = "wsuredcrosswarriors@gmail.com";
    }


    @Override
    public EmailBuilder setHtmlCode(String message) throws FileNotFoundException {
        this.htmlCode = new String(String.valueOf(new Scanner(new File("src/main/resources/templates/contactEmail.html")).useDelimiter("\\Z").next()));
        this.htmlCode = htmlCode.replace("MESSAGE", message);
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

    @Override
    public Email build() {
        return super.build();
    }
}
