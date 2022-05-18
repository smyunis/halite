package com.smyunis.halite.web.controllers;

import com.smyunis.halite.application.caterer.CatererService;
import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.domain.shared.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatererController {

    private final CatererService catererService;

    @Autowired
    public CatererController(CatererService catererService) {
        this.catererService = catererService;
    }

    @GetMapping("/")
    Integer getHello() {
       return 7;
    }
}
