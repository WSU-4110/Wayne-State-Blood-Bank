package org.redcrosswarriors.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.redcrosswarriors.controllerservice.FeedbackControllerService;
import org.redcrosswarriors.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestFeedbackController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedbackControllerService service;

    @WithMockUser(username="admin@wayne.edu",roles={"USER","ADMIN"})
    @Test
    public void testCreateFeedback() throws Exception{
        Feedback feedback = new Feedback();
        feedback.setMessage("Hello World");
        when(service.createFeedback(eq("admin@wayne.edu"), any()))
                .thenReturn(new ResponseEntity<>("",HttpStatus.CREATED));
        Gson gson = new Gson();
        String json = gson.toJson(feedback);

        this.mockMvc.perform(
                post("/feedback").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andExpect(status().isCreated());
    }
}
