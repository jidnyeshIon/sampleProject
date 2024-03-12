package com.example.sampleProject.data;


import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "ROOM_NUMBER")
    private String  room_number;
    @Column(name = "BED_TYPE")
    private  String bedInfo ;
    @Column(name ="CHECK_IN_DATE")
    private Date checkInDate;

    @Column(name ="CHECK_OUT_DATE")
    private Date checkOutDate;


    @Column(name = "IS_BOOKED")
    private boolean isBooked;

    public Room(Long id, Date checkInDate, Date checkOutDate) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Room(Long id, String name, String room_number, String bedInfo, boolean isBooked) {
        this.id = id;
        this.name = name;
        this.room_number = room_number;
        this.bedInfo = bedInfo;
        this.isBooked = isBooked;
    }

    public Room() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getBedInfo() {
        return bedInfo;
    }

    public void setBedInfo(String bedInfo) {
        this.bedInfo = bedInfo;
    }
    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

//    @Override

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", room_number='" + room_number + '\'' +
                ", bedInfo='" + bedInfo + '\'' +
                ", isBooked=" + isBooked +
                '}';
    }
//    public String toString() {
//        return "Room{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", roomNumber='" + room_number + '\'' +
//                ", bedInfo='" + bedInfo + '\'' +
//                '}';
//    }
}
