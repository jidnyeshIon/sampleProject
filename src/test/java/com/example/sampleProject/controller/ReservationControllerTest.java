package com.example.sampleProject.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.sampleProject.data.Guest;
import com.example.sampleProject.data.Room;
import com.example.sampleProject.service.ReservationService;
import com.example.sampleProject.service.RoomService;
import com.example.sampleProject.utli.MyRequestDTO;
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

@ContextConfiguration(classes = {ReservationController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ReservationControllerTest {
  @Autowired
  private ReservationController reservationController;

  @MockBean
  private ReservationService reservationService;

  @MockBean
  private RoomService roomService;

  /**
   * Method under test: {@link ReservationController#bookroom(MyRequestDTO)}
   */
  @Test
  void testBookroom() throws Exception {
    // Arrange
    doNothing().when(reservationService).bookRoom(Mockito.<Room>any(), Mockito.<Guest>any());

    Room room = new Room();
    room.setBedInfo("Bed Info");
    room.setBooked(true);
    room.setId(1L);
    room.setName("Name");
    room.setRoom_number("42");
    when(roomService.findRoomByRoomNumber(Mockito.<String>any())).thenReturn(room);

    Guest guest = new Guest();
    guest.setAddress("42 Main St");
    guest.setCountry("GB");
    guest.setEmailAddress("42 Main St");
    guest.setFirstName("Jane");
    guest.setId(1L);
    guest.setLastName("Doe");
    guest.setPhoneNumber("6625550144");
    guest.setState("MD");

    MyRequestDTO myRequestDTO = new MyRequestDTO();
    myRequestDTO.setGuest(guest);
    myRequestDTO.setRoomNo("Room No");
    String content = (new ObjectMapper()).writeValueAsString(myRequestDTO);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/reservations/book")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(reservationController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  /**
   * Method under test: {@link ReservationController#getAllVacantRooms()}
   */
  @Test
  void testGetAllVacantRooms() throws Exception {
    // Arrange
    when(reservationService.getAllVacantRooms()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reservations/status");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(reservationController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().string("[]"));
  }
}
