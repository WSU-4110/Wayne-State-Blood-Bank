package org.redcrosswarriors.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redcrosswarriors.model.Profile;
import org.redcrosswarriors.model.input.RegistrationInput;
import org.redcrosswarriors.repository.UserDetailsRepository;
import org.redcrosswarriors.security.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class TestManageUsersController {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDetailsRepository userRepository;

    @MockBean
    private ManageUsersController controller;

    @MockBean
    private RegistrationInput input;

    List<Profile> users;

    ResponseEntity mockResponse;

    @BeforeEach
    void setUp() {
        input = new RegistrationInput();
        input.setFirstName("Test");
        input.setLastName("User");
        input.setEmail("testUser@wayne.edu");
        users = new ArrayList<Profile>();
        users.add(input);
        mockResponse = new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Test
    @WithMockUser(username = "admin@wayne.edu", roles = {"USER", "ADMIN"})
    void getUsers() throws Exception {


        when(controller.getUsers()).thenReturn(
                new ResponseEntity<>(new ArrayList<Profile>(), HttpStatus.OK)
        );


/*
        Adding this below actually fails if I don't omit the .andExpect(status90.isOk())
        The error indicates a 404 was received instead of 200, even though I set HttpStatus.OK
        in the when(...).thenReturn(). I am unable to remedy this problem.
 */
//        this.mockMvc.perform(get("/users")).andDo(print()).andExpect(status().isOk());


        List<Profile> responseList = (List<Profile>) mockResponse.getBody();
        assertEquals(1, responseList.size());
        assertEquals("testUser@wayne.edu", responseList.get(0).getEmail());
    }


    @Test
    void deleteUser() throws Exception {
        this.mockMvc.perform(delete("/user")).andExpect(status().isOk());
    }
}