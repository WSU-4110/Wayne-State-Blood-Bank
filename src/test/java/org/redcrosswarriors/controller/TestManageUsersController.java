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

    @MockBean
    private UserDetailsRepository userRepository;

    private ManageUsersController controller;

    private RegistrationInput input;

    List<Profile> users;

    @BeforeEach
    void setUp() {
        input = new RegistrationInput();
        input.setFirstName("Test");
        input.setLastName("User");
        input.setEmail("testUser@wayne.edu");
        users = new ArrayList<>();
        users.add(input);
    }

    @Test
    @WithMockUser(username = "admin@wayne.edu", roles = {"USER", "ADMIN"})
    void getUsers() throws Exception {

        String expectedResult = "[{\"firstName\":\"Test\",\"lastName\":\"User\",\"phoneNumber\":null,\"bloodDonorStatus\":null,\"bloodType\":null,\"birthDay\":null,\"email\":\"testUser@wayne.edu\",\"password\":null}]";
        when(userRepository.getAllProfiles()).thenReturn(users);


        MvcResult result = this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk()).andReturn();
        String jSonResponse = result.getResponse().getContentAsString();

        System.out.println(jSonResponse);

        assertEquals(expectedResult, jSonResponse);
    }


    @Test
    @WithMockUser(username = "admin@wayne.edu", roles = {"USER", "ADMIN"})
    void deleteUser() throws Exception {
        this.mockMvc.perform(delete("/user")).andExpect(status().isOk());
    }
}