package org.redcrosswarriors.emailservice;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;
import java.util.Scanner;

public class Email {
    private String myEmail = null;
    private String myPW = null;
    private Properties properties = null;
    private String[] recipients = null;
    private String htmlCode = null;
    private String subject;


    private Email(){};

    public Email(String htmlCode, String recipients[], String subject){
        this.htmlCode = htmlCode;
        this.recipients = recipients.clone();
        this.subject = subject;

        myEmail = "wsuredcrosswarriors@gmail.com";
        myPW = "give_blood_2020";
        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
    }


    public void send() {
        try {
            System.out.println("Sending mail");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myEmail, myPW);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setSubject(subject);

            for (String address : recipients) {
                System.out.println("Sending to : " + address);
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
                message.setContent(htmlCode, "text/html");
                Transport.send(message);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * for unit testing purposes
     *
     * @return : return the html string that would be inserted into email
     */
    public String getHtmlCode() {
        return htmlCode;
    }

    /**
     * for unit testing purposes
     *
     * @return array of recipients
     */
    public String[] getRecipients() {
        return recipients;
    }


}
