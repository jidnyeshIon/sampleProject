package com.example.sampleProject.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.sampleProject.data.BedType;
import com.example.sampleProject.data.Room;
import com.example.sampleProject.service.RoomService;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {RoomController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class RoomControllerTest {
    @Autowired
    private RoomController roomController;

    @MockBean
    private RoomService roomService;

    /**
     * Method under test: {@link RoomController#getRooms(Model)}
     */
    @Test
    void testGetRooms() throws Exception {
        // Arrange
        when(roomService.getRooms()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rooms");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(roomController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("rooms"))
                .andExpect(MockMvcResultMatchers.view().name("hotel-rooms"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("hotel-rooms"));
    }

    /**
     * Method under test: {@link RoomController#addNew(Model)}
     */
    @Test
    void testAddNew() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/addnew");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(roomController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("roomForm"))
                .andExpect(MockMvcResultMatchers.view().name("add-room"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("add-room"));
    }

    /**
     * Method under test: {@link RoomController#addRooms(Room)}
     */
    @Test
    void testAddRooms() throws Exception {
        // Arrange
        Room room = new Room();
        room.setBedInfo(BedType.Single);
        room.setCheckInDate(mock(Date.class));
        room.setCheckOutDate(mock(Date.class));
        room.setId(1L);
        room.setName("Name");
        room.setRoom_number("42");
        when(roomService.addRooms(Mockito.<Room>any())).thenReturn(room);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(roomController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:index.html"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("index.html"));
    }

    /**
     * Method under test: {@link RoomController#addNew(Model)}
     */
    @Test
    void testAddNew2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/addnew", "Uri Variables");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(roomController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("roomForm"))
                .andExpect(MockMvcResultMatchers.view().name("add-room"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("add-room"));
    }
}
