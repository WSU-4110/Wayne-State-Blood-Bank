package org.redcrosswarriors.controllerservice;


import org.redcrosswarriors.emailservice.SendNotificationEmail;

import org.redcrosswarriors.model.RequestedTimeDetails;
import org.redcrosswarriors.model.ReturnForRequestBlood;
import org.redcrosswarriors.model.input.RequestBloodInput;
import org.redcrosswarriors.repository.RequestBloodDetailsRepository;
import org.redcrosswarriors.repository.RequestTimeDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequestBloodControllerService
{
    @Autowired
    private RequestBloodDetailsRepository requestRepository;

    @Autowired
    private RequestTimeDetailsRepository requestTimeRepository;



    private RequestedTimeDetails timeDetails;

    @Transactional
    public ResponseEntity<Object> requestBlood(RequestBloodInput requestInput, String email)
    {
        Map<String,String> json = new HashMap<String, String>();
        boolean check = true;
        String message = "";
        try{

            System.out.println("start");
            timeDetails = requestTimeRepository.getRequestedTimeByEmail(requestInput.getEmail());

            String currentTime;
            if(timeDetails == null)
            {
                currentTime = returnTime();
                System.out.println(currentTime);
                requestTimeRepository.requesterTimeUpdate(requestInput.getEmail(), currentTime);
                message = findMatches(requestInput, requestRepository);

            }
            else
            {
                String lastTime;
                lastTime = timeDetails.getTime();
                currentTime = returnTime();
                if(findDifference(lastTime, currentTime))
                {
                    requestTimeRepository.updateTime(currentTime, requestInput.getEmail());
                    message = findMatches(requestInput, requestRepository);


                }
                else
                {
                    //check = t;
                    System.out.println("Sorry you have to wait 24 hrs for a new request!");
                    message = "Hey " + requestInput.getFirstName() + ", your request cannot be processed now. You have already made a request within last 24 hrs. Blood can be requested every 24 hrs for each account holder.";
                }
            }
            json.put("message", message);


        }
        catch (Exception e){
            check = false;
            e.printStackTrace();
        }

        return new ResponseEntity<Object>(json, HttpStatus.OK);
    }

    public String findMatches(RequestBloodInput input, RequestBloodDetailsRepository requestRepository)
    {
        String message = "";
        List<String> matches;
        try
        {

            requestRepository.newRequester(input.getFirstName(),
                    input.getLastName(),
                    input.getEmail(),
                    input.getPhoneNumber(),
                    input.getBloodType(),
                    input.getHospitalName(),
                    input.getStreetName(),
                    input.getCityName(),
                    input.getStateName(),
                    input.getZipCode(),
                    input.getMessage());

            matches = requestRepository.findMatches(input.getBloodType());
            if(matches.size() == 0)
            {
                message = "Sorry " + input.getFirstName() + ", unfortunately we couldn't find any matching donors for your requested blood type.";
            }
            else if(matches.size() == 1)
                message = "Hey " + input.getFirstName() + ", we found " + Integer.toString(matches.size()) + " match for you. This donor has been notified.";
            else
                message = "Hey " + input.getFirstName() + ", we found " + Integer.toString(matches.size()) + " matches for you. These donors has been notified.";
            SendNotificationEmail sendRequest = new SendNotificationEmail(matches, input);
            sendRequest.start();
        }
        catch (Exception e){
            message = "Your request was not processes successfully. Sorry";
            e.printStackTrace();
        }
        return message;

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
