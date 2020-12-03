package org.redcrosswarriors.controller;

import org.junit.jupiter.api.Test;
import org.redcrosswarriors.controllerservice.ViewRequestControllerService;
import org.redcrosswarriors.model.RequestInputDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestViewBloodRequestController
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ViewRequestControllerService service;

    @WithMockUser(username="admin@wayne.edu",roles={"USER","ADMIN"})
    @Test
    public void testViewResults() throws Exception{
        when(service.getAllRequests("admin@wayne.edu")).thenReturn(new ResponseEntity<>(new ArrayList<RequestInputDetails>(), HttpStatus.OK));
        this.mockMvc.perform(
                get("/viewRequests")
        ).andDo(print()).andExpect(status().isOk());
    }
}
