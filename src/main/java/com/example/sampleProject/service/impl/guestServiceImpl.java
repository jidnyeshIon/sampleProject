package com.example.sampleProject.service.impl;

import com.example.sampleProject.data.Guest;
import com.example.sampleProject.data.repository.GuestRepository;
import com.example.sampleProject.service.GuestService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class guestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;

    public guestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public List<Guest> getAllGuest() {
       return this.guestRepository.findAll();
    }

    @Override
    public Guest addGuest(Guest guest) {
        return this.guestRepository.save(guest);
    }

    @Override
    public Guest findGuestByEmailAddress(String value) {
//        System.out.println("Getting email in guestService :" + value);
        return this.guestRepository.findGuestByEmailAddress(value);
    }
}
