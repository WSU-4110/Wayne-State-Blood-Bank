package org.redcrosswarriors.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.redcrosswarriors.controllerservice.ProfileControllerService;
import org.redcrosswarriors.model.Profile;
import org.redcrosswarriors.model.input.EditProfileInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestProfileController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileControllerService service;

    @Test
    @WithMockUser(username="admin@wayne.edu",roles={"USER","ADMIN"})
    public void testEditProfile() throws Exception {
        EditProfileInput input = new EditProfileInput();
        input.setFirstName("John");
        input.setLastName("Doe");
        input.setPassword("Password1234");
        input.setBloodDonorStatus("Y");
        input.setPhoneNumber("1111111111");

        Map<String, Object> responseObject = new HashMap<String, Object>();
        responseObject.put("message", "Successfully updated profile");
        when(service.updateProfile("admin@wayne.edu", input)).thenReturn(
                new ResponseEntity<Object>(responseObject, HttpStatus.OK)
        );
        Gson gson = new Gson();
        String json = gson.toJson(input);
        this.mockMvc.perform(put("/profile").contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username="admin@wayne.edu",roles={"USER","ADMIN"})
    public void testEditProfileBadInput() throws Exception {
        EditProfileInput input = new EditProfileInput();
        input.setFirstName("John1");
        input.setLastName("Doe2");
        input.setPassword("bad_password");
        input.setBloodDonorStatus("INvalid status");
        input.setPhoneNumber("abc123");

        Map<String, Object> responseObject = new HashMap<String, Object>();
        responseObject.put("message", "Successfully updated profile");
        when(service.updateProfile("admin@wayne.edu", input)).thenReturn(
                new ResponseEntity<Object>(responseObject, HttpStatus.OK)
        );

        Gson gson = new Gson();
        String json = gson.toJson(input);
        this.mockMvc.perform(put("/profile").contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isBadRequest());

    }

    @Test
    @WithMockUser(username = "admin@wayne.edu", roles={"USER", "ADMIN"})
    public void testGetProfile() throws Exception{

        when(service.getProfile("admin@wayne.edu")).thenReturn(
                new ResponseEntity<>(new Profile(), HttpStatus.OK)
        );
        this.mockMvc.perform(get("/profile")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin@wayne.edu", roles={"USER", "ADMIN"})
    public void testDeleteProfile() throws Exception{
        when(service.deleteProfile("admin@wayne.edu")).thenReturn(
                new ResponseEntity<>(HttpStatus.OK)
        );
        this.mockMvc.perform(delete("/profile")).andDo(print()).andExpect(status().isOk());
    }
}
