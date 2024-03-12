package com.automation.api.tests;

import com.automation.api.endpoints.BookingEndPoints;
import com.automation.api.payload.Booking;
import com.automation.api.payload.BookingDates;
import com.automation.api.utilities.PropertiesConfiguration;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BookingTests {

    Faker faker;
    Booking bookingPayload;

    BookingEndPoints bookingEndPoints = new BookingEndPoints();
    String authToken;

    @BeforeClass
    public void setUpData(){

        faker = new Faker();
        bookingPayload = new Booking();

        String sUsername = PropertiesConfiguration.getPropertyValueByKey("username");
        String sPassword = PropertiesConfiguration.getPropertyValueByKey("password");

        //username and password for auth
        bookingPayload.setUsername(sUsername);
        bookingPayload.setPassword(sPassword);

        //set data
        bookingPayload.setFirstName(faker.name().firstName());
        bookingPayload.setLastName(faker.name().lastName());
        bookingPayload.setTotalPrice(faker.number().numberBetween(70, 150));

        //set booking dates
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckIn(faker.date().future(30, TimeUnit.DAYS));
        bookingDates.setCheckIn(faker.date().future(60, TimeUnit.DAYS));

        bookingPayload.setAdditionalNeeds(faker.options().option("breakfast", "extra towel"));


        //run to execute token before test
        authToken = generateToken();
    }


    public String generateToken() {

        Response response = bookingEndPoints.authCreateToken(this.bookingPayload);

        // Extracting token from the response
        String token = response.jsonPath().getString("token");
        System.out.println("Token is " + token);

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token not found in response.");
        }

        return token;
    }

    @Test
    public void testCreateBooking(){

        Response response = bookingEndPoints.createBooking(this.bookingPayload, authToken);
        response.then().log().all();

        // Provide a meaningful assertion message
        Assert.assertEquals(response.getStatusCode(), 200, "Booking creation failed.");
    }
}
