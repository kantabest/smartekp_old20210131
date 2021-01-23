package com.easyworks.smartekp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {
    
    @GetMapping("reservation/reservationCar")
    public String reservationCar() {
        return "reservation/reservationCar";
    }
    
    @GetMapping("reservation/reservationMeetingroom")
    public String reservationMeetingroom() {
        return "reservation/reservationMeetingroom";
    }
    
    @GetMapping("reservation/reservationCondo")
    public String reservationCondo() {
        return "reservation/reservationCondo";
    }  
    
    @GetMapping("reservation/reservationCard")
    public String reservationCard() {
        return "reservation/reservationCard";
    }      

}
