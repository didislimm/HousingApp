package com.avdeenko_mironov.HousingSystem.controllers;

import com.avdeenko_mironov.HousingSystem.services.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuantityOfFreeFlatController {

    private final HouseService houseService;

    @RequestMapping(value = "/quantityOfFreeFlat", method = RequestMethod.POST)
    public String validateValues(@ModelAttribute("numberOfHouse") int numberOfHouse,
                                 @ModelAttribute("street") String street, Model model){
        List<Integer> quantityOfFreeFlat = houseService.getQuantityOfFreeFlat(street, numberOfHouse);
        String textByNumberOfFreeFlat = "Number of free flat = " + quantityOfFreeFlat.size();
        String textByNumbersOfFreeFlat = "Numbers of free flats: ";
        for (int i = 0; i < quantityOfFreeFlat.size(); i++) {
            textByNumbersOfFreeFlat += quantityOfFreeFlat.get(i);
            if(i+1!= quantityOfFreeFlat.size()){
                textByNumbersOfFreeFlat+=", ";
            }
        }
        textByNumbersOfFreeFlat+=".";
        model.addAttribute("numberOfFreeFlat", textByNumberOfFreeFlat);
        model.addAttribute("quantityOfFreeFlat", textByNumbersOfFreeFlat);
        return "quantityOfFreeFlat-2-step";
    }
}
