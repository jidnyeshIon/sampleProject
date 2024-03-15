package com.example.sampleProject.data;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID")
    private Long id;
    @Column(name = "ROOM_ID")
    private Long roomId;
    @Column(name = "GUEST_ID")
    private Long guestId ;

    @Column(name = "RES_DATE")
    private Date date;

    @Column(name = "CHECK_IN_DATE")
    private  Date checkInDate;

    @Column(name = "CHECK_OUT_DATE")
    private Date checkOutDate;

    public Reservation(Date checkInDate, Date checkOutDate) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Reservation(Long id, Long roomId, Long guestId, Date date) {
        this.id = id;
        this.roomId = roomId;
        this.guestId = guestId;
        this.date = date;
    }


    public Reservation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        return "Reservation{" +
                "reservationId=" + id +
                ", roomId=" + roomId +
                ", guestId=" + guestId +
                ", reservationDate=" + date +
                '}';
    }
}
