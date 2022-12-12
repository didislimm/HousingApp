package com.avdeenko_mironov.HousingSystem.controllers;


import org.springframework.web.bind.annotation.GetMapping;

public class ErrorController {
    @GetMapping("/error")
    public String error(){
        return "errorPage/index";
    }
}
