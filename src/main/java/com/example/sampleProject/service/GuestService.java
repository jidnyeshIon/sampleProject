package com.example.sampleProject.service;

import com.example.sampleProject.data.Guest;

import java.util.List;

public interface GuestService {
    public List<Guest> getAllGuest();
    public Guest addGuest(Guest guest);


}
