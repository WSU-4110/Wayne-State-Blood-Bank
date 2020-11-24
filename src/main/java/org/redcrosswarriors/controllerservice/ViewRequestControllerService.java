package org.redcrosswarriors.controllerservice;

import org.redcrosswarriors.model.RequestInputDetails;
import org.redcrosswarriors.model.ViewFeedback;
import org.redcrosswarriors.model.viewRequest;
import org.redcrosswarriors.repository.RequestBloodDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ViewRequestControllerService
{
    @Autowired
    private RequestBloodDetailsRepository requestRepository;



    public ResponseEntity<List<RequestInputDetails>> getAllRequests(String email)
    {

        List<RequestInputDetails> requestLists = requestRepository.viewRequests(email);
        /*
        ArrayList<viewRequest> newRequestLists = new ArrayList<>();
        for(int i = 0; i < requestLists.size(); i++)
        {
            newRequestLists.get(i).setFirstName(requestLists.get(i).getFirstName());
            newRequestLists.get(i).setLastName(requestLists.get(i).getLastName());
            newRequestLists.get(i).setEmail(requestLists.get(i).getEmail());
            newRequestLists.get(i).setPhoneNumber(requestLists.get(i).getPhoneNumber());
            newRequestLists.get(i).setBloodType(requestLists.get(i).getBloodType());
            newRequestLists.get(i).setHospitalName(requestLists.get(i).getHospitalName());
            newRequestLists.get(i).setStreet(requestLists.get(i).getStreet());
            newRequestLists.get(i).setCity(requestLists.get(i).getCity());
            newRequestLists.get(i).setState(requestLists.get(i).getState());
            newRequestLists.get(i).setZipCode(requestLists.get(i).getZipCode());
            newRequestLists.get(i).setMessage(requestLists.get(i).getMessage());
        }

         */

        return new ResponseEntity<List<RequestInputDetails>>(requestLists, HttpStatus.OK);
    }

}
