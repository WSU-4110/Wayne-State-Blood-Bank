package org.redcrosswarriors.controllerservice;

import org.redcrosswarriors.emailservice.send_mail;
import org.redcrosswarriors.model.AccountDetails;
import org.redcrosswarriors.model.RequestedTimeDetails;
import org.redcrosswarriors.model.VerificationToken;
import org.redcrosswarriors.model.input.RegistrationInput;
import org.redcrosswarriors.model.input.RequestBloodInput;
import org.redcrosswarriors.repository.RequestBloodDetailsRepository;
import org.redcrosswarriors.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RequestBloodControllerService
{
    @Autowired
    private RequestBloodDetailsRepository requestRepository;

    @Autowired
    private RequestedTimeDetails timeDetails;

    @Transactional
    public boolean requestBlood(RequestBloodInput requestInput, String email)
    {
        requestRepository.newRequester(requestInput.getFirstName(),
                requestInput.getLastName(),
                requestInput.getEmail(),
                requestInput.getPhoneNumber(),
                requestInput.getBloodType(),
                requestInput.getHospitalName(),
                requestInput.getStreet(),
                requestInput.getCity(),
                requestInput.getZipCode(),
                requestInput.getMessage());

        timeDetails = requestRepository.getRequestedTimeByEmail(email);

        if(timeDetails == null)
        {
            String currentTime = returnTime();
            requestRepository.requesterTimeUpdate(email, currentTime);
            List<String> matches;
            matches = requestRepository.findMatches(requestInput.getBloodType());
        }

        return true;
    }

    public String returnTime()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
