package org.redcrosswarriors.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redcrosswarriors.model.BloodDrive;
import org.redcrosswarriors.repository.BloodDriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestBloodDriveController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BloodDriveRepository repository;

    private Gson gson;

    @BeforeEach
    public void init(){
        gson = new Gson();
    }

    @Test
    public void testGetBloodDrives() throws Exception{
        List<BloodDrive> bloodDrives = new ArrayList<>();
        bloodDrives.add(new BloodDrive(
                1,
                "Wayne State Blood Drive",
                "Detroit MI",
                "12:00:00",
                "2020-11-11",
                "Blood drive today",
                "www.wayne.edu"
        ));
        bloodDrives.add(new BloodDrive(
                2,
                "Wayne State Blood Drive",
                "Detroit MI",
                "12:00:00",
                "2020-11-11",
                "Blood drive today",
                "www.wayne.edu"
        ));
        Iterable<BloodDrive> testBloodDrives = (Iterable<BloodDrive>) bloodDrives;
        when(repository.findAll()).thenReturn(testBloodDrives);
        MvcResult result = this.mockMvc.perform(get("/bloodDrive")).andDo(print()).andExpect(
                status().isOk()
        ).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        BloodDrive[] returnedBloodDrives = gson.fromJson(jsonResponse, BloodDrive[].class);
        assertEquals(returnedBloodDrives.length, 2);
    }

    @Test
    @WithMockUser(username="admin@wayne.edu",roles={"USER","ADMIN"})
    public void testCreateBloodDrive() throws Exception{
        BloodDrive testBloodDrive = new BloodDrive(
                "Wayne State Blood Drive",
                "Detroit MI",
                "12:00:00",
                "2020-11-11",
                "Blood drive today",
                "www.wayne.edu"
        );
        String json = gson.toJson(testBloodDrive);
        this.mockMvc.perform(post("/bloodDrive").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username="admin@wayne.edu",roles={"USER","ADMIN"})
    public void testDeleteBloodDrive() throws Exception{
        this.mockMvc.perform(delete("/bloodDrive/1")).andDo(print()).andExpect(status().isOk());
    }
}
