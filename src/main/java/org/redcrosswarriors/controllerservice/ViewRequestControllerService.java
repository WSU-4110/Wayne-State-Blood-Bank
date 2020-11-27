package org.redcrosswarriors.controllerservice;

import org.redcrosswarriors.model.RequestInputDetails;
import org.redcrosswarriors.repository.RequestBloodDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ViewRequestControllerService
{
    @Autowired
    private RequestBloodDetailsRepository requestRepository;



    public ResponseEntity<List<RequestInputDetails>> getAllRequests(String email)
    {

        List<RequestInputDetails> requestLists = requestRepository.viewRequests(email);

        return new ResponseEntity<List<RequestInputDetails>>(requestLists, HttpStatus.OK);
    }

}
