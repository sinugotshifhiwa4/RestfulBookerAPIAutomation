package com.automation.api.endpoints;

import com.automation.api.payload.Booking;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class BookingEndPoints {

    private final Routes routes = new Routes();

    public Response authCreateToken(Booking payload){

        Response response = given()
                .contentType("application/json")
                .accept("application/json")
                .body(payload)

                .when()
                .post(routes.authTokenUrl);

        return response;
    }

    public Response createBooking(Booking payload, String authToken){

        Response response = given()
                .contentType("application/json")
                .accept("application/json")
                .body(payload)
                .header("Authorization", "Bearer " + authToken)

                .when()
                .post(routes.postUrl);

        return response;
    }
}
