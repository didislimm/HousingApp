package com.avdeenko_mironov.HousingSystem.controllers;


import com.avdeenko_mironov.HousingSystem.services.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class FlatStatusController {
    private final HouseService houseService;

    @RequestMapping(value = "/checkFlatStatus", method = RequestMethod.POST)
    public String validateValues(@ModelAttribute("numberOfHouse") int numberOfHouse,
                                 @ModelAttribute("street") String street, Model model){
        List<Integer> numbersOfHouses=  houseService.findHousesByStreet(street);
        if (!numbersOfHouses.contains(numberOfHouse)){
            Collections.sort(numbersOfHouses);
            model.addAttribute("numbers", numbersOfHouses);
            return "checkFlatStatus";
        }
        List<Integer> flatsStatus = houseService.checkFlatStatus(street, numberOfHouse);
        String text = "In the house at " + street + " " + numberOfHouse +
            ", the following apartments do not meet the GOST standard: ";
        for (int i = 0; i < flatsStatus.size(); i++) {
            text += flatsStatus.get(i);
            if(i+1!= flatsStatus.size()){
                text+=", ";
            }
        }
        text+=".";
        model.addAttribute("flatsStatus", text);
        return "checkFlatStatus-2-step";
    }
}
