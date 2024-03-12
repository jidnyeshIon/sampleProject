package com.example.sampleProject.utli;

import com.example.sampleProject.data.Guest;

// Class entity to pass room no and guest object to the endpoint
public class MyRequestDTO {
    private String roomNo;
    private Guest guest;

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @Override
    public String toString() {
        return "MyRequestDTO{" +
                "roomNo='" + roomNo + '\'' +
                ", guest=" + guest.toString() +
                '}';
    }
}
