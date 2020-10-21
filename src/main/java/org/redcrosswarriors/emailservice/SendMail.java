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
public class SendMail {
    private String myEmail = null;
    private String myPW = null;
    private Properties properties = null;
    private String recipient;
    private String URL = null;
    private String recipients[] = null;
    private Request request = null;


    /**
     * default constructor
     * must set these properties for all cases
     */
    SendMail() {
        myEmail = "wsuredcrosswarriors@gmail.com";
        myPW = "give_blood_2020";
        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
    }

    /**
     * verification constructor
     * use this constructor when sending an account verification message
     * @param _recipient email address
     * @param _URL : link to insert into email message
     */
    public SendMail(String _recipient, String _URL){
        this();
        recipient = _recipient;
        URL = _URL;
    }

    /**
     * match notification constructor
     * use this constructor when sending match notifications
     * @param _recipients : Array of email addresses
     * @param _request : object of type request
     */
    public SendMail(String _recipients[], Request _request){
        this();
        //copy _recipients
        recipients = new String[_recipients.length];
        for (int i = 0; i<_recipients.length; i++) {
            recipients[i] = _recipients[i];
        };
        request = new Request(_request);
    }


    /**
     * method for sending account verification emails
     * @throws Exception
     */
    public void send_verification ()throws Exception{
        //create email session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, myPW);
            }
        });

        //create message to be sent
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Red Cross Warriors Account Validation");
            String htmlCode = new String(String.valueOf(new Scanner(new File("src/main/resources/templates/verification_email.html")).useDelimiter("\\Z").next()));
            htmlCode = htmlCode.replace("URL_to_click",URL);
            message.setContent(htmlCode,"text/html");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // send message
        Transport.send(message);

        System.out.println("verification email sent");
    }


    /**
     * method for sending match notification emails to as many email addresses are in the recipients[] array.
     * @throws Exception
     */
    public void send_notification ()throws Exception{
        System.out.println("notification method");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, myPW);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myEmail));
        message.setSubject("YOUR Blood is Needed!");

        for(int i = 0; i<recipients.length; i++)
        {

            try {
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients[i]));
                String htmlCode = new String(String.valueOf(new Scanner(new File("src/main/resources/templates/match_notification.html")).useDelimiter("\\Z").next()));
                htmlCode = htmlCode.replace("NAME",request.getName());
                htmlCode = htmlCode.replace("EMAIL_ADDRESS",request.getEmailAddress());
                htmlCode = htmlCode.replace("MESSAGE",request.getMessage());
                htmlCode = htmlCode.replace("PHONE_NUMBER",request.getPhoneNumber());

                message.setContent(htmlCode,"text/html");
                Transport.send(message);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
