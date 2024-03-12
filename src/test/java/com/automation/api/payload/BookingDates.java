package com.automation.api.payload;

import java.security.SecureRandom;
import java.util.Date;

public class BookingDates {

    private Date checkIn;
    private Date checkOut;


    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }
}
