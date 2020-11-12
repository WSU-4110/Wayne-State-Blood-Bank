package org.redcrosswarriors.emailservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VerificationEmailBuilder extends EmailBuilder{

    public VerificationEmailBuilder(){
        subject = "Red Cross Warriors Account Verification";
    }


    public EmailBuilder setRecipient(String recipient) {
        recipients = new String[1];
        recipients[0] = recipient;
        return this;
    }

    @Override
    public EmailBuilder setHtmlCode(String link) throws FileNotFoundException {
        this.htmlCode = new String(String.valueOf(new Scanner(new File("src/main/resources/templates/verificationEmail.html")).useDelimiter("\\Z").next()));
        this.htmlCode = htmlCode.replace("URL_to_click", link);
        return this;
    }

    @Override
    public EmailBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    @Override
    public EmailBuilder setRecipients(String[] recipients) {
        return this;
    }


}
