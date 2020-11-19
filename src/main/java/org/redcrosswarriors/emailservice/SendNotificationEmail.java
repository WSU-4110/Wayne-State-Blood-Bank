package org.redcrosswarriors.emailservice;

import org.redcrosswarriors.model.input.RequestBloodInput;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SendNotificationEmail extends SendMail {

    /**
     * Constructor
     *
     * @param recipients : array of email addresses of matches to be notified
     * @param requestInput     : Request object. Elements of this are inserted to html email template and sent to recipients
     * @throws Exception :
     */
    public SendNotificationEmail(List<String> recipients, RequestBloodInput requestInput) throws Exception {
        this.recipients = new String[recipients.size()];
        for(int i =0; i<recipients.size(); i++){
            this.recipients[i] = recipients.get(i);
        }

        subject = "YOUR Blood is Needed!";
        String HTML_TEMPLATE;
        HTML_TEMPLATE = String.valueOf(new Scanner(new File("src/main/resources/templates/matchNotification.html")).useDelimiter("\\Z").next());
     //   htmlCode = HTML_TEMPLATE.replace("NAME", requestInput.getFirstName()+" "+requestInput.getLastName());
        htmlCode = HTML_TEMPLATE.replace("NAME", "donor");
        htmlCode = htmlCode.replace("BLOOD_TYPE", requestInput.getBloodType());
        htmlCode = htmlCode.replace("EMAIL_ADDRESS", requestInput.getEmail());
        htmlCode = htmlCode.replace("PHONE_NUMBER", requestInput.getPhoneNumber());
        htmlCode = htmlCode.replace("MESSAGE", requestInput.getMessage());
        htmlCode = htmlCode.replace("HOSPITAL", requestInput.getHospitalName());
        htmlCode = htmlCode.replace("STREET", requestInput.getStreetName());
        htmlCode = htmlCode.replace("CITY", requestInput.getCityName());
        htmlCode = htmlCode.replace("ZIP_CODE", requestInput.getZipCode());
    }
}
