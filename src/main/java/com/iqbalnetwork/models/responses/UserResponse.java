package com.iqbalnetwork.models.responses;

import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;
}

class Address {
    private String street;
    class Geo {
        String lat;
        String lng;
    }
}

@Data
class Company {
    String name;
    String catchPhrase;
    String bs;
}