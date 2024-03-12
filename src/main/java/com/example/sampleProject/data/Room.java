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
    private String room_number;
    @Enumerated(EnumType.STRING)
    @Column(name = "BED_TYPE")
    private BedType bedInfo;
    @Column(name = "CHECK_IN_DATE")
    private Date checkInDate;

    @Column(name = "CHECK_OUT_DATE")
    private Date checkOutDate;


    public Room(Long id, Date checkInDate, Date checkOutDate) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Room(Long id, String name, String room_number, BedType bedInfo) {
        this.id = id;
        this.name = name;
        this.room_number = room_number;
        this.bedInfo = bedInfo;

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

    public BedType getBedInfo() {
        return bedInfo;
    }

    public void setBedInfo(BedType bedInfo) {
        this.bedInfo = bedInfo;
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

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", room_number='" + room_number + '\'' +
                ", bedInfo=" + bedInfo +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}




