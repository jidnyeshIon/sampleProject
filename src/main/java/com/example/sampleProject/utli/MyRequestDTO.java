package com.example.sampleProject.utli;

import com.example.sampleProject.data.Guest;

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
}
