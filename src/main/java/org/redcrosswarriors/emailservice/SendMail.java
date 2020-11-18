package org.redcrosswarriors.emailservice;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * Base class (abstract) for sending emails
 * Create object of type subclass you need, then
 * Then send your email with the send_verification() or send_notification() method
 */
abstract public class SendMail extends Thread {
    protected String myEmail;
    protected String myPW;
    protected ArrayList<String> recipients;
    protected Properties properties;
    protected String htmlCode;
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
     * call this method to start the below run() method in a new thread
     */
    public void start() {
        super.start();
    }

    /**
     * NOTE: This should NOT be called directly:
     * Calling this method with object.run() will result in this method being run
     * in the main thread instead of a new one. To start this method in a new thread, call with object.start()
     */
    public void run() {
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

            for (Object recipient : recipients) {
                System.out.println("Sending to : " + recipient);
                message.setRecipient(Message.RecipientType.TO, new InternetAddress((String) recipient));
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
    public List<String> getRecipients() {
        return recipients;
    }
}
