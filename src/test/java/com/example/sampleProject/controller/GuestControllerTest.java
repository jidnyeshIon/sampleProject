package com.example.sampleProject.controller;

import static org.mockito.Mockito.when;

import com.example.sampleProject.data.Guest;
import com.example.sampleProject.service.GuestService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {GuestController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class GuestControllerTest {
    @Autowired
    private GuestController guestController;

    @MockBean
    private GuestService guestService;

    /**
     * Method under test: {@link GuestController#getAllGuest()}
     */
    @Test
    void testGetAllGuest() throws Exception {
        // Arrange
        when(guestService.getAllGuest()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/guest/");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(guestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link GuestController#addGuest(Guest)}
     */
    @Test
    void testAddGuest() throws Exception {
        // Arrange
        Guest guest = new Guest();
        guest.setAddress("42 Main St");
        guest.setCountry("GB");
        guest.setEmailAddress("42 Main St");
        guest.setFirstName("Jane");
        guest.setId(1L);
        guest.setLastName("Doe");
        guest.setPhoneNumber("6625550144");
        guest.setState("MD");
        when(guestService.addGuest(Mockito.<Guest>any())).thenReturn(guest);

        Guest guest2 = new Guest();
        guest2.setAddress("42 Main St");
        guest2.setCountry("GB");
        guest2.setEmailAddress("42 Main St");
        guest2.setFirstName("Jane");
        guest2.setId(1L);
        guest2.setLastName("Doe");
        guest2.setPhoneNumber("6625550144");
        guest2.setState("MD");
        String content = (new ObjectMapper()).writeValueAsString(guest2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/guest/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(guestController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"address\":\"42 Main St\",\"country\":\"GB\",\"state\":\"MD\","
                                        + "\"emailAddress\":\"42 Main St\",\"phoneNumber\":\"6625550144\"}"));
    }
}
