/*
* Unit test for the HelloWorldController
*/
package org.redcrosswarriors.controller;

import org.junit.jupiter.api.Test;
import org.redcrosswarriors.controllerservice.HelloWorldControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.ArrayList;
import org.redcrosswarriors.model.Message;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
public class TestHelloWorldController {
    
    // auto wired spring will automatically 
    // inject the mocked model view controller instance
    @Autowired
    private MockMvc mockMvc;

    // create a mocked version of the service.
    // this way the code from the service doesn't actually run
    // and we can make sure we're only testing the controller in this unit test
    @MockBean
    private HelloWorldControllerService service;

    // make a request to the get message endpoint and assert
    // that a list of messages is returned.
    @Test
    @WithMockUser(username = "admin", authorities = { "ROLE_ADMIN", "ROLE_USER" })
    public void getAllMessagesTest() throws Exception {
        // simulate a list of messages from the database without actually
        // having to access the database
        when(service.getAllMessages()).thenReturn(getTestMessageData());
        // make sure that it returns a json array of messages
        this.mockMvc.perform(get("/message")).andDo(print()).andExpect(status().isOk())
        .andExpect(
            content().json(
                "[ {'id':1, 'message':'Hello World!'}, {'id':2, 'message':'Second Message'}]"));
    }

    private List<Message> getTestMessageData(){
        List<Message> testMessages = new ArrayList<>();
        testMessages.add(new Message(1, "Hello World!"));
        testMessages.add(new Message(2,"Second Message"));
        return testMessages;
    }

}

