package com.example.sampleProject.controller;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.sampleProject.data.BedType;
import com.example.sampleProject.data.Room;
import com.example.sampleProject.data.repository.RoomRepository;
import com.example.sampleProject.service.ReservationService;
import com.example.sampleProject.service.RoomService;
import com.example.sampleProject.service.impl.RoomServiceImpl;
import com.example.sampleProject.utli.RoomResStatus;

import java.sql.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

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
   * Method under test: {@link ReservationController#saveBooking(long, Model)}
   */
  @Test
  void testSaveBooking() throws Exception {
    // Arrange
    doNothing().when(reservationService).saveBooking(anyLong(), Mockito.<String>any());

    Room room = new Room();
    room.setBedInfo(BedType.Single);
    room.setCheckInDate(mock(Date.class));
    room.setCheckOutDate(mock(Date.class));
    room.setId(1L);
    room.setName("Name");
    room.setRoom_number("42");
    when(roomService.findRoomByRoomNumber(Mockito.<String>any())).thenReturn(room);
    MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/saveBooking");
    MockHttpServletRequestBuilder requestBuilder = getResult.param("guestId", String.valueOf(1L));

    // Act and Assert
    MockMvcBuilders.standaloneSetup(reservationController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isFound())
            .andExpect(MockMvcResultMatchers.model().size(0))
            .andExpect(MockMvcResultMatchers.view().name("redirect:index.html"))
            .andExpect(MockMvcResultMatchers.redirectedUrl("index.html"));
  }

  /**
   * Method under test: {@link ReservationController#saveBooking(long, Model)}
   */
  @Test
  void testSaveBooking2() throws Exception {
    // Arrange
    doNothing().when(reservationService).saveBooking(anyLong(), Mockito.<String>any());

    Room room = new Room();
    room.setBedInfo(BedType.Single);
    room.setCheckInDate(mock(Date.class));
    room.setCheckOutDate(mock(Date.class));
    room.setId(1L);
    room.setName("Name");
    room.setRoom_number("42");
    when(roomService.findRoomByRoomNumber(Mockito.<String>any())).thenReturn(room);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/saveBooking")
            .param("guestId", "https://example.org/example");

    // Act
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(reservationController)
            .build()
            .perform(requestBuilder);

    // Assert
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link ReservationController#saveBooking(long, Model)}
   */
  @Test
  void testSaveBooking3() throws Exception {
    // Arrange
    doNothing().when(reservationService).saveBooking(anyLong(), Mockito.<String>any());

    Room room = new Room();
    room.setBedInfo(BedType.Single);
    room.setCheckInDate(mock(Date.class));
    room.setCheckOutDate(mock(Date.class));
    room.setId(1L);
    room.setName("Name");
    room.setRoom_number("42");
    when(roomService.findRoomByRoomNumber(Mockito.<String>any())).thenReturn(room);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/saveBooking")
            .param("guestId", "Printing from saveBooking");

    // Act
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(reservationController)
            .build()
            .perform(requestBuilder);

    // Assert
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link ReservationController#saveBooking(long, Model)}
   */
  @Test
  void testSaveBooking4() throws Exception {
    // Arrange
    doNothing().when(reservationService).saveBooking(anyLong(), Mockito.<String>any());

    Room room = new Room();
    room.setBedInfo(BedType.Single);
    room.setCheckInDate(mock(Date.class));
    room.setCheckOutDate(mock(Date.class));
    room.setId(1L);
    room.setName("Name");
    room.setRoom_number("42");
    when(roomService.findRoomByRoomNumber(Mockito.<String>any())).thenReturn(room);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/saveBooking").param("guestId", "");

    // Act
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(reservationController)
            .build()
            .perform(requestBuilder);

    // Assert
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test: {@link ReservationController#saveBooking(long, Model)}
   */
  @Test
  void testSaveBooking5() throws Exception {
    // Arrange
    doNothing().when(reservationService).saveBooking(anyLong(), Mockito.<String>any());

    Room room = new Room();
    room.setBedInfo(BedType.Single);
    room.setCheckInDate(mock(Date.class));
    room.setCheckOutDate(mock(Date.class));
    room.setId(1L);
    room.setName("Name");
    room.setRoom_number("42");
    when(roomService.findRoomByRoomNumber(Mockito.<String>any())).thenReturn(room);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/saveBooking")
            .param("guestId", "https://example.org/example", "42");

    // Act
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(reservationController)
            .build()
            .perform(requestBuilder);

    // Assert
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  /**
   * Method under test:
   * {@link ReservationController#checkAvailability(RoomResStatus, Model)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testCheckAvailability() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   jakarta.servlet.ServletException: Request processing failed: java.lang.NullPointerException: Name is null
    //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
    //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
    //   java.lang.NullPointerException: Name is null
    //       at java.base/java.lang.Enum.valueOf(Enum.java:271)
    //       at com.example.sampleProject.data.BedType.valueOf(BedType.java:3)
    //       at com.example.sampleProject.controller.ReservationController.checkAvailability(ReservationController.java:79)
    //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
    //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange
    ReservationService reservationService = mock(ReservationService.class);
    ReservationController reservationController = new ReservationController(reservationService,
            new RoomServiceImpl(mock(RoomRepository.class)));
    RoomResStatus roomResStatus = new RoomResStatus("2020-03-01", "2020-03-01", "Bed Type");

    // Act
    reservationController.checkAvailability(roomResStatus, new ConcurrentModel());
  }

  /**
   * Method under test: {@link ReservationController#chooseRoom(Model)}
   */
  @Test
  void testChooseRoom() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/chooseRoom");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(reservationController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().size(1))
            .andExpect(MockMvcResultMatchers.model().attributeExists("room"))
            .andExpect(MockMvcResultMatchers.view().name("get-room-choice"))
            .andExpect(MockMvcResultMatchers.forwardedUrl("get-room-choice"));
  }

  /**
   * Method under test: {@link ReservationController#chooseRoom(Model)}
   */
  @Test
  void testChooseRoom2() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/chooseRoom");
    requestBuilder.contentType("");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(reservationController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().size(1))
            .andExpect(MockMvcResultMatchers.model().attributeExists("room"))
            .andExpect(MockMvcResultMatchers.view().name("get-room-choice"))
            .andExpect(MockMvcResultMatchers.forwardedUrl("get-room-choice"));
  }

  /**
   * Method under test: {@link ReservationController#getRequirement(Model)}
   */
  @Test
  void testGetRequirement() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/book-room");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(reservationController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().size(1))
            .andExpect(MockMvcResultMatchers.model().attributeExists("requirementInfo"))
            .andExpect(MockMvcResultMatchers.view().name("requirement-info"))
            .andExpect(MockMvcResultMatchers.forwardedUrl("requirement-info"));
  }

  /**
   * Method under test: {@link ReservationController#getRequirement(Model)}
   */
  @Test
  void testGetRequirement2() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/book-room");
    requestBuilder.contentType("");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(reservationController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().size(1))
            .andExpect(MockMvcResultMatchers.model().attributeExists("requirementInfo"))
            .andExpect(MockMvcResultMatchers.view().name("requirement-info"))
            .andExpect(MockMvcResultMatchers.forwardedUrl("requirement-info"));
  }

  /**
   * Method under test: {@link ReservationController#saveRoomChoice(Room)}
   */
  @Test
  void testSaveRoomChoice() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveRoomChoice");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(reservationController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isFound())
            .andExpect(MockMvcResultMatchers.model().size(0))
            .andExpect(MockMvcResultMatchers.view().name("redirect:/addNewGuest"))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/addNewGuest"));
  }

  /**
   * Method under test: {@link ReservationController#saveRoomChoice(Room)}
   */
  @Test
  void testSaveRoomChoice2() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveRoomChoice");
    requestBuilder.contentType("text/plain");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(reservationController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isFound())
            .andExpect(MockMvcResultMatchers.model().size(0))
            .andExpect(MockMvcResultMatchers.view().name("redirect:/addNewGuest"))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/addNewGuest"));
  }

  /**
   * Method under test: {@link ReservationController#saveRoomChoice(Room)}
   */
  @Test
  void testSaveRoomChoice3() throws Exception {
    // Arrange
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveRoomChoice");
    requestBuilder.contentType("https://example.org/example");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(reservationController)
            .build()
            .perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isFound())
            .andExpect(MockMvcResultMatchers.model().size(0))
            .andExpect(MockMvcResultMatchers.view().name("redirect:/addNewGuest"))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/addNewGuest"));
  }
}
