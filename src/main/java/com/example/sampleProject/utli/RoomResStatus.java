package com.example.sampleProject.utli;


import com.example.sampleProject.data.BedType;

public class RoomResStatus {

    private String checkInDate;
    private String  checkOutDate;
    private String bedType;

    public RoomResStatus(){};
    public RoomResStatus(String checkInDate, String checkOutDate, String bedType) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bedType = bedType;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }
}
