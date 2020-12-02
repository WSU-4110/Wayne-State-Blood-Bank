package org.redcrosswarriors.controllerservice;

import org.junit.jupiter.api.Test;
import org.redcrosswarriors.model.input.RequestBloodInput;
import org.redcrosswarriors.repository.RequestBloodDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestRequestBloodControllerService {

    @MockBean
    private RequestBloodDetailsRepository requestRepository;

    @Autowired
    private RequestBloodControllerService service;

    @Test
    public void testFindMatches(){
        RequestBloodInput input  = new RequestBloodInput();

        input.setFirstName("John");
        input.setLastName("Doe");
        input.setEmail("johhn@wayne.edu");
        input.setPhoneNumber("0000000000");
        input.setBloodType("AB+");
        input.setHospitalName("Beaumont");
        input.setStreetName("1234 XYZ");
        input.setCityName("ABC");
        input.setStateName("MI");
        input.setZipCode("48084");
        input.setMessage("I need Blood");

        List<String> matches = new ArrayList<>();
        matches.add("user@wayne.edu");
        matches.add("user2@wayne.edu");
        when(requestRepository.findMatches(input.getBloodType())).thenReturn(matches);
        String actualMessage = "Hey " + input.getFirstName() + ", we found " + Integer.toString(matches.size()) + " matches for you. These donors has been notified.";
        String message = service.findMatches(input, requestRepository);
        assertEquals(2, (matches.size()));
        assertEquals(actualMessage, message);
    }

    @Test
    public void TestTrueFindDifference(){
        String start_date = "01-11-2020 01:48:38";
        String end_date = "01-11-2020 02:48:38";
        boolean actual = service.findDifference(start_date, end_date);
        assertEquals(false, actual);
    }

    @Test
    public void TestFalseFindDifference(){
        String start_date = "01-11-2020 01:48:38";
        String end_date = "02-11-2020 02:48:38";
        boolean actual = service.findDifference(start_date, end_date);
        assertEquals(true, actual);
    }




}
