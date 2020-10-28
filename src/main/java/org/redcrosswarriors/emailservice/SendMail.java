package org.redcrosswarriors.emailservice;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;
import java.util.Scanner;


/**
 * class for sending emails
 * Create object of type send_mail using the constructor that matches your need.
 * Then send your email with the send_verification() or send_notification() method
 */
abstract public class SendMail {
    protected String myEmail = null;
    protected String myPW = null;
    protected String[] recipients;
    protected Properties properties = null;
    protected String htmlCode = null;
    protected String subject;


    /**
     * default constructor
     * must set these properties for all cases
     */
    protected SendMail() {
        myEmail = "wsuredcrosswarriors@gmail.com";
        myPW = "give_blood_2020";
        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
    }

    /**
     * sends the email to the recipients in the recipients[] array built in subclass constructor
     * @throws Exception :
     */
    public void send() throws Exception {
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

            try {
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
                message.setContent(htmlCode, "text/html");
                Transport.send(message);

            } catch (Exception e) {
                e.printStackTrace();
            }
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
