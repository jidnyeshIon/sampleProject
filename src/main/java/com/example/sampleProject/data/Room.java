package com.example.sampleProject.data;


import jakarta.persistence.*;

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
    @Column(name = "BED_INFO")
    private String bedInfo;



    @Column(name = "IS_BOOKED")
    private boolean isBooked;


    public Room(Long id, String name, String room_number, String bedInfo,boolean isBooked) {
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


//    @Override
//    public String toString() {
//        return "Room{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", roomNumber='" + room_number + '\'' +
//                ", bedInfo='" + bedInfo + '\'' +
//                '}';
//    }
}
