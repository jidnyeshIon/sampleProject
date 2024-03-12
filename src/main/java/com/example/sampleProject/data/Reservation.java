package com.example.sampleProject.data;

import jakarta.persistence.*;

import java.util.Date;

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

//    @Column(name = "CHECK_IN_DATE")
//    private Date check_in_date;

//    @Column(name = "CHRCK_OUT_DATE")
//    private Date check_out_date;

//    public Reservation(Date check_in_date, Date check_out_date) {
//        this.check_in_date = check_in_date;
//        this.check_out_date = check_out_date;
//    }

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

//    public Date getCheck_in_date() {
//        return check_in_date;
//    }
//
//    public void setCheck_in_date(Date check_in_date) {
//        this.check_in_date = check_in_date;
//    }
//
//    public Date getCheck_out_date() {
//        return check_out_date;
//    }
//
//    public void setCheck_out_date(Date check_out_date) {
//        this.check_out_date = check_out_date;
//    }

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
