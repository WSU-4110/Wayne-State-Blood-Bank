package org.redcrosswarriors.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TestLoginController {

    @Autowired
    private MockMvc mockMvc;


    @WithMockUser(username="admin@example.com",roles={"USER","ADMIN"})
    @Test
    public void testGetLoginStatus() throws Exception{

        this.mockMvc.perform(get("/loginStatus")).andDo(print()).andExpect(status().isOk())
                .andExpect(
                        content().json("{\"isLoggedIn\": true, \"userEmail\": \"admin@example.com\"}"));
    }
}
