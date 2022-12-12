package com.avdeenko_mironov.HousingSystem.controllers;

import com.avdeenko_mironov.HousingSystem.model.repo.FlatRepository;
import com.avdeenko_mironov.HousingSystem.model.repo.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MainController {

   private final HouseRepository houseRepository;

    @RequestMapping( value = "/control",method = RequestMethod.POST)
    public String defineTypeOfMethod(@ModelAttribute("street") String street, @ModelAttribute("type") String type, Model model){

        return type;
    }
}
