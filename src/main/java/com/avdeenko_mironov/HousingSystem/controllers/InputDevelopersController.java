package com.avdeenko_mironov.HousingSystem.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class InputDevelopersController {
    @GetMapping("/developers")
    public String outputStreets() {
        return "developers";
    }
}
