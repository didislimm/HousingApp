package com.avdeenko_mironov.HousingSystem.controllers;

import com.avdeenko_mironov.HousingSystem.model.House;
import com.avdeenko_mironov.HousingSystem.model.repo.FlatRepository;
import com.avdeenko_mironov.HousingSystem.model.repo.FloorRepository;
import com.avdeenko_mironov.HousingSystem.model.repo.HouseRepository;
import com.avdeenko_mironov.HousingSystem.services.CreateHouseService;
import com.avdeenko_mironov.HousingSystem.services.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CreateHouseController {

    private final FlatRepository flatRepository;
    private final FloorRepository floorRepository;
    private final HouseRepository houseRepository;
    private final HouseService houseService;
    private final CreateHouseService createHouseService;

    @RequestMapping(value = "/createHouse-2-step", method = RequestMethod.POST)
    public String validateValues(@ModelAttribute("numberOfHouse") int numberOfHouse,
                                 @ModelAttribute("valueOfFloors") int valueOfFloors,
                                 @ModelAttribute("valueOfFlats") int valueOfFlats,
                                 @ModelAttribute("street") String street, Model model){
        List<Integer> numbersOfHouses= houseService.findHousesByStreet(street);
        for (int i=0;i<numbersOfHouses.size();i++){
            if (numberOfHouse==numbersOfHouses.get(i)){
                model.addAttribute("numbers", numbersOfHouses);
                return "createHouse";
            }
        }
        List<String> flats=new ArrayList<>();
        for (int i=1;i<=valueOfFlats;i++){
            flats.add("Flat "+i);
        }
        model.addAttribute("flats",flats);
        model.addAttribute("valueOfFloors",valueOfFloors);
        model.addAttribute("valueOfFlats",valueOfFlats);
        model.addAttribute("street",street);
        return "/createHouse-2-step";
    }

    @RequestMapping(value = "/creatingHouse", method = RequestMethod.POST)
    public String createHouse(@ModelAttribute("numberOfHouse") int numberOfHouse,
                              @ModelAttribute("valueOfFloors") int valueOfFloors,
                              @ModelAttribute("street") String street,
                              Model model,@RequestParam("flat") Double[] flats ){

        House house= createHouseService.createRandomHouse(flats,valueOfFloors,street,numberOfHouse);



        return "main";
    }

}
