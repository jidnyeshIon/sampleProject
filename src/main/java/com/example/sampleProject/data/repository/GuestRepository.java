package com.example.sampleProject.data.repository;


import com.example.sampleProject.data.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest,Long> {


    @Query("SELECT g FROM Guest g WHERE g.emailAddress = :value")
    Guest findGuestByEmailAddress(@Param("value") String value);

}
