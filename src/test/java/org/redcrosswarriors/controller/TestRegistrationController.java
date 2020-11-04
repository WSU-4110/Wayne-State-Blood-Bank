//package org.redcrosswarriors.controller;
//
//import com.google.gson.Gson;
//import org.junit.jupiter.api.Test;
//import org.redcrosswarriors.controllerservice.ProfileControllerService;
//import org.redcrosswarriors.controllerservice.RegistrationControllerService;
//import org.redcrosswarriors.model.input.EditProfileInput;
//import org.redcrosswarriors.model.input.RegistrationInput;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class TestRegistrationController
//{
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private RegistrationControllerService service;
//
//    @Test
//    public void testRegister() throws Exception
//    {
//        RegistrationInput input = new RegistrationInput();
//        input.setEmail("go1277@wayne.edui");
//        input.setFirstName("John");
//        input.setLastName("Doe");
//        input.setBirthDay("2000-05-05");
//        input.setBloodDonorStatus("Y");
//        input.setPhoneNumber("1234567891");
//        input.setBloodType("A+");
//
//        Map<String, Object> responseObject = new HashMap<String, Object>();
//        when(service.registerAccount(input)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
//        this.mockMvc.perform(post("/registration")).andDo(print()).andExpect(status().isOk());
//    }
//}
