package org.redcrosswarriors.controllerservice;


import org.redcrosswarriors.emailservice.SendNotificationEmail;
import org.redcrosswarriors.model.AccountDetails;
//import org.redcrosswarriors.model.RequestedTimeDetails;
import org.redcrosswarriors.model.RequestedTimeDetails;
import org.redcrosswarriors.model.VerificationToken;
import org.redcrosswarriors.model.input.RegistrationInput;
import org.redcrosswarriors.model.input.RequestBloodInput;
import org.redcrosswarriors.repository.RequestBloodDetailsRepository;
import org.redcrosswarriors.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class RequestBloodControllerService
{
    @Autowired
    private RequestBloodDetailsRepository requestRepository;


    private RequestedTimeDetails timeDetails = new RequestedTimeDetails();

    @Transactional
    public boolean requestBlood(RequestBloodInput requestInput)
    {
        boolean check = true;
        try{
            System.out.println("start");
            requestRepository.newRequester(requestInput.getFirstName(),
                    requestInput.getLastName(),
                    requestInput.getEmail(),
                    requestInput.getPhoneNumber(),
                    requestInput.getBloodType(),
                    requestInput.getHospitalName(),
                    requestInput.getStreet(),
                    requestInput.getCity(),
                    requestInput.getState(),
                    requestInput.getZipCode(),
                    requestInput.getMessage());


            System.out.println(requestInput.getLastName());
            System.out.println(requestInput.getEmail());
            System.out.println(requestInput.getPhoneNumber());
            System.out.println(requestInput.getBloodType());
            System.out.println(requestInput.getHospitalName());

            System.out.println("start");

            timeDetails = requestRepository.getRequestedTimeByEmail(requestInput.getEmail());
            String currentTime = returnTime();
            if(timeDetails == null)
            {
                currentTime = returnTime();
                System.out.println(currentTime);
                requestRepository.requesterTimeUpdate(requestInput.getEmail(), currentTime);
                List<String> matches;

                matches = requestRepository.findMatches(requestInput.getBloodType());
                if(matches.size() == 0)
                {
                    System.out.println("0 matches near by");
                    check = false;

                }
                else
                {
                    SendNotificationEmail sendRequest = new SendNotificationEmail(matches, requestInput);
                    sendRequest.start();
                }

            }
            else
            {
                String lastTime;
                lastTime = timeDetails.getTime();
                currentTime = returnTime();
                if(findDifference(lastTime, currentTime))
                {
                    requestRepository.updateTime(currentTime, requestInput.getEmail());
                    List<String> matches;
                    matches = requestRepository.findMatches(requestInput.getBloodType());
                    SendNotificationEmail sendRequest = new SendNotificationEmail(matches, requestInput);
                    sendRequest.start();
                }
                else
                {
                    check = false;
                    System.out.println("Sorry you have to wait 24 hrs for a new request!");
                }
            }


        }
        catch (Exception e){
            check = false;
            e.printStackTrace();
        }

        return check;
    }

    public String returnTime()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public boolean findDifference(String start_date, String end_date)
    {

        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
                = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm:ss");
        boolean check = false;

        // Try Block
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            // Calucalte time difference
            // in milliseconds
            long difference_In_Time
                    = d2.getTime() - d1.getTime();

            // Calucalte time difference in
            // seconds, minutes, hours, years,
            // and days
            long difference_In_Seconds
                    = (difference_In_Time
                    / 1000)
                    % 60;

            long difference_In_Minutes
                    = (difference_In_Time
                    / (1000 * 60))
                    % 60;

            long difference_In_Hours
                    = (difference_In_Time
                    / (1000 * 60 * 60))
                    % 24;

            long difference_In_Years
                    = (difference_In_Time
                    / (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days
                    = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                    % 365;

            // Print the date difference in
            // years, in days, in hours, in
            // minutes, and in seconds

            System.out.print(
                    "Difference "
                            + "between two dates is: ");

            System.out.println(
                    difference_In_Years
                            + " years, "
                            + difference_In_Days
                            + " days, "
                            + difference_In_Hours
                            + " hours, "
                            + difference_In_Minutes
                            + " minutes, "
                            + difference_In_Seconds
                            + " seconds");
            if(difference_In_Days>=1)
                check = true;
            else
            {
                check = false;
            }
        }

        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }

        return check;
    }


    public int numberOfMatches(String BloodType)
    {
        int number = 0;
        switch(BloodType)
        {
            case "A+":
                number = requestRepository.aPlus();
                break;
            case "B+":
                number = requestRepository.bPlus();
                break;
            case "AB+":
                number = requestRepository.abPlus();
                break;
            case "O+":
                number = requestRepository.oPlus();
                break;
            case "A-":
                number = requestRepository.aNeg();
                break;
            case "B-":
                number = requestRepository.bNeg();
                break;
            case "AB-":
                number = requestRepository.abNeg();
                break;
            case "O-":
                number = requestRepository.oNeg();
                break;
            default:
                number = 0;

        }
        return number;
    }

}
