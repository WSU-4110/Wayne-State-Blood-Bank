package org.redcrosswarriors.controllerservice;

import org.junit.jupiter.api.Test;
import org.redcrosswarriors.model.RequestInputDetails;
import org.redcrosswarriors.repository.RequestBloodDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestViewRequestControllerService {

    @MockBean
    private RequestBloodDetailsRepository requestRepository;


    @Autowired
    ViewRequestControllerService service;

    @Test
    public void testGetRequests(){
        RequestInputDetails request1 = new RequestInputDetails();
        request1.setFirstName("John");
        request1.setLastName("Doe");
        request1.setEmail("johhn@wayne.edu");
        request1.setPhoneNumber("0000000000");
        request1.setBloodType("AB+");
        request1.setHospitalName("Beaumont");
        request1.setStreet("1234 XYZ");
        request1.setCity("ABC");
        request1.setState("MI");
        request1.setZipCode("48084");
        request1.setMessage("I need Blood");


        RequestInputDetails request2 = new RequestInputDetails();
        request2.setFirstName("Johny");
        request2.setLastName("Doen");
        request2.setEmail("johhny@wayne.edu");
        request2.setPhoneNumber("1100000000");
        request2.setBloodType("AB+");
        request2.setHospitalName("Beaumont");
        request2.setStreet("1234 ABC");
        request2.setCity("XYZ");
        request2.setState("MI");
        request2.setZipCode("48083");
        request2.setMessage("I need Blood donor");
        List<RequestInputDetails> bloodRequestList = new ArrayList<>();
        bloodRequestList.add(request1);
        bloodRequestList.add(request2);
        when(requestRepository.viewRequests("admin@wayne.edu")).thenReturn(bloodRequestList);
        ResponseEntity<Object> response = service.getAllRequests("admin@wayne.edu");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, ((List) response.getBody()).size());
    }
}
