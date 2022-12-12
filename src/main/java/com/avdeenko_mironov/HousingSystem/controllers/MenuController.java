package com.avdeenko_mironov.HousingSystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {
    @GetMapping("/")
    public String greeting() {
        return "main";
    }

}