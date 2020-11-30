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

    // returns test data for blood drives
    private Iterable<BloodDrive> getTestBloodDrives(){
        List<BloodDrive> bloodDrives = new ArrayList<>(2);
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
        Iterable<BloodDrive> testBloodDrives = bloodDrives;

        return testBloodDrives;
    }

    @Test
    public void testGetBloodDrives() throws Exception{
        Iterable<BloodDrive> testBloodDrives = getTestBloodDrives();
        // have the repository return our test data when findAll is called instead of returning data from the database
        // this ensures that the test is repeatable
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

    @Test
    public void testGetUpcomingDrives() throws Exception{
        List<BloodDrive> bloodDrives = (List<BloodDrive>) getTestBloodDrives();
        // set the repository to return a list of testBloodDrives so that the test is repeatable
        when(repository.findUpcomingBloodDrives()).thenReturn(bloodDrives);
        MvcResult result = this.mockMvc.perform(get("/bloodDrive/upcoming")).andDo(print()).andExpect(
                status().isOk()
        ).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        BloodDrive[] returnedBloodDrives = gson.fromJson(jsonResponse, BloodDrive[].class);
        // ensure that we get a json response that can be converted into our blood drive class and that
        // we have exactly 2 blood drive objects returned in the array.
        assertEquals(returnedBloodDrives.length, 2);
    }

    // run this test with a user that has the role of admin
    @Test
    @WithMockUser(username="admin@wayne.edu",roles={"USER","ADMIN"})
    public void testDeleteOutdatedBloodDrives() throws Exception{
        this.mockMvc.perform(delete("/bloodDrive/outdated")).andDo(print()).andExpect(status().isOk());
    }

}
