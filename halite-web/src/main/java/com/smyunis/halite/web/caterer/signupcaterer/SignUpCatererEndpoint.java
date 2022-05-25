package com.smyunis.halite.web.caterer.signupcaterer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/caterers/")
public class SignUpCatererEndpoint {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signUpCaterer(){}
}
