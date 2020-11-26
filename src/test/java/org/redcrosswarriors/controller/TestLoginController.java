package org.redcrosswarriors.controller;

import org.junit.jupiter.api.Test;
import org.redcrosswarriors.repository.AccountDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class TestLoginController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountDetailsRepository repository;


    @WithMockUser(username="admin@example.com",roles={"USER","ADMIN"})
    @Test
    public void testGetLoginStatus() throws Exception{
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        roles.add("ADMIN");
        when(repository.getRolesByEmail("admin@example.com")).thenReturn(roles);
        this.mockMvc.perform(get("/loginStatus")).andDo(print()).andExpect(status().isOk())
                .andExpect(
                        content().json("{\"roles\": [\n" +
                                "\"USER\"," +
                                "\"ADMIN\"" +
                                "], \"isLoggedIn\": true, \"userEmail\": \"admin@example.com\"}"));
    }
}
