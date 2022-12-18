package com.avdeenko_mironov.HousingSystem.controllers;


import com.avdeenko_mironov.HousingSystem.model.Flat;
import com.avdeenko_mironov.HousingSystem.model.Floor;
import com.avdeenko_mironov.HousingSystem.model.House;
import com.avdeenko_mironov.HousingSystem.services.HouseService;
import com.avdeenko_mironov.HousingSystem.services.StreetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;


@Controller
@RequiredArgsConstructor
public class OutputHouseController {

    private final HouseService houseService;
    private final StreetService streetService;

    @RequestMapping(value = "/outputHouse-2-step", method = RequestMethod.POST)
    public String validateValues(@ModelAttribute("numberOfHouse") int numberOfHouse,
                                 @ModelAttribute("street") String street,Model model){
        List<Integer> numbersOfHouses= houseService.findHousesByStreet(street);
            if (!numbersOfHouses.contains(numberOfHouse)){
                model.addAttribute("numbers", numbersOfHouses);
                return "outputHouse";
            }
            House house=houseService.getHouse(numberOfHouse,street);
            List<String> text = new ArrayList<>();
            text.add("Address:"+street+','+numberOfHouse);
            text.add("Total lodgers:"+ houseService.getTotalLodgersOfHouse(numberOfHouse,street));
            text.add("Total Area:"+ houseService.getTotalAreaOfHouse(numberOfHouse,street));
            for (Floor floor:house.getFloors()){
                text.add("Floor "+floor.getNumberOfFloor()+":");
                    for (Flat flat:floor.getFlats()){
                        text.add(flat.toString());
                    }
            }
        model.addAttribute("house",text);

        return "/outputHouse-2-step";
    }
}
