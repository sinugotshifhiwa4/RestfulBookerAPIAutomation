package com.automation.api.endpoints;

public class Routes {

    public String baseUrl= "https://restful-booker.herokuapp.com";


    public String authTokenUrl = baseUrl + "/auth";
    public String postUrl = baseUrl + "/booking";
    public String getUrl = baseUrl + "/booking/{id}";
    public String UpdateUrl = baseUrl + "/booking/{id}";
    public String deleteUrl = baseUrl + "/booking/{id}";
}
