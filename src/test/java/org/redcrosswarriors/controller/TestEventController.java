package org.redcrosswarriors.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redcrosswarriors.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import org.redcrosswarriors.model.Event;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestEventController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventRepository repository;

    private Gson gson;

    @BeforeEach
    public void init(){
        gson = new Gson();
    }

    private void setTestData(List<Event> events){
        events.add(new Event(1, "test", "Hello World", "2020-11-12"));
        events.add(new Event(2, "test", "Hello World", "2020-11-12"));
        when(repository.findAll()).thenReturn(events);
        when(repository.getUpcomingEvents()).thenReturn(events);
    }

    @Test
    public void testGetEvents() throws Exception{
        testGetEventsEndpoint("/event");
    }

    private void testGetEventsEndpoint(String endpoint) throws Exception {
        List<Event> events = new ArrayList<>();
        setTestData(events);
        MvcResult result = this.mockMvc.perform(get(endpoint))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        Event[] returnedEvents = gson.fromJson(jsonResponse, Event[].class);
        assertEquals(2, returnedEvents.length);
    }

    @Test
    public void testGetUpcomingEvents() throws Exception{
        testGetEventsEndpoint("/event/upcoming");
    }

    @Test
    @WithMockUser(username="admin@wayne.edu",roles={"USER","ADMIN"})
    public void testCreateEvent() throws Exception{
        Event event = new Event("test", "test", "2020-11-12");
        String json = gson.toJson(event);
        this.mockMvc.perform(post("/event").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username="admin@wayne.edu",roles={"USER","ADMIN"})
    public void testDeleteEvent() throws Exception{
        this.mockMvc.perform(delete("/event/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin@wayne.edu",roles={"USER","ADMIN"})
    public void testDeleteOutdatedEvents() throws Exception{
        this.mockMvc.perform(delete("/event/outdated")).andDo(print()).andExpect(status().isOk());
    }

}
