package com.mindhub.XNHomeBanking.dto;

import org.springframework.web.bind.annotation.RequestParam;

public class CreateClientDTO {

    private String  firstname, lastname, email, password;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
