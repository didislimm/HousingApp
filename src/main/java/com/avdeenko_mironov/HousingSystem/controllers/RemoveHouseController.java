package com.avdeenko_mironov.HousingSystem.controllers;

import com.avdeenko_mironov.HousingSystem.model.House;
import com.avdeenko_mironov.HousingSystem.services.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RemoveHouseController {

    private final HouseService houseService;

    @RequestMapping(value = "/removeHouse", method = RequestMethod.POST)
    public String validateValues(@ModelAttribute("numberOfHouse") int numberOfHouse,
                                 @ModelAttribute("street") String street, Model model){
        houseService.removeHouse(street, numberOfHouse);
        return "main";
    }
}
