package org.redcrosswarriors.controller;

import org.junit.jupiter.api.Test;
import org.redcrosswarriors.controllerservice.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestVerificationController {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private VerificationTokenService service;

    @Test
    public void testVerifyUser() throws Exception{
        when(service.verifyUser("ABC-123")).thenReturn(
                new ResponseEntity<>("user successfully verified please log in again to access your account",
                        HttpStatus.OK)
        );

        this.mockMvc.perform(get("/verify/ABC-123")).andDo(print()).andExpect(status().isOk())
                .andExpect(
                        content().string("user successfully verified please log in again to access your account"));
    }
}
