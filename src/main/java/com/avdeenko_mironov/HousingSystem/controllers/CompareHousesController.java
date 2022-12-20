package com.avdeenko_mironov.HousingSystem.controllers;

import com.avdeenko_mironov.HousingSystem.model.House;
import com.avdeenko_mironov.HousingSystem.model.Street;
import com.avdeenko_mironov.HousingSystem.model.repo.FlatRepository;
import com.avdeenko_mironov.HousingSystem.model.repo.FloorRepository;
import com.avdeenko_mironov.HousingSystem.model.repo.HouseRepository;
import com.avdeenko_mironov.HousingSystem.services.CreateHouseService;
import com.avdeenko_mironov.HousingSystem.services.HouseService;
import com.avdeenko_mironov.HousingSystem.services.StreetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompareHousesController {

    private final FlatRepository flatRepository;
    private final FloorRepository floorRepository;
    private final HouseRepository houseRepository;

    private final StreetService streetService;
    private final HouseService houseService;
    private final CreateHouseService createHouseService;

    @GetMapping(value = "/compareHouses")
    public String inputStreets(Model model){
        List<Street> streets=streetService.getAllStreets();
        model.addAttribute("streets",streets);
        return "compareHouses";
    }

    @RequestMapping(value = "/compareHouses-2-step", method = RequestMethod.POST)
    public String outputHousesInStreets(@ModelAttribute("street1") String street1,
                                        @ModelAttribute("street2") String street2, Model model){
        List<Integer> numbersOfHouses1= houseService.findHousesByStreet(street1);
        List<Integer> numbersOfHouses2= houseService.findHousesByStreet(street2);
        model.addAttribute("street1",street1);
        model.addAttribute("street2",street2);
        model.addAttribute("numbersOfHouses1",numbersOfHouses1);
        model.addAttribute("numbersOfHouses2",numbersOfHouses2);
        return "/compareHouses-2-step";
    }

    @RequestMapping(value = "/compareHouses-3-step", method = RequestMethod.POST)
    public String compareHouses(@ModelAttribute("numberOfHouse1") int numberOfHouse1,
                                @ModelAttribute("numberOfHouse2") int numberOfHouse2,
                                @ModelAttribute("street1") String street1,
                                @ModelAttribute("street2") String street2, Model model){
        if (numberOfHouse1==numberOfHouse2 && street1.equals(street2)){
            model.addAttribute("numbersOfHouses1", houseService.findHousesByStreet(street1));
            model.addAttribute("numbersOfHouses2", houseService.findHousesByStreet(street2));
            return "compareHouses-2-step";
        }
        House house1=houseService.getHouse(numberOfHouse1,street1);
        House house2=houseService.getHouse(numberOfHouse2,street2);
        int lodgersInFirstHouse=houseService.getTotalLodgersOfHouse(house1);
        int lodgersInSecondHouse=houseService.getTotalLodgersOfHouse(house2);
        double squareInFirstHouse=houseService.getTotalAreaOfHouse(house1);
        double squareInSecondHouse=houseService.getTotalAreaOfHouse(house2);

        List<String> text=new ArrayList<>();
        text.add("First House");
        text.add("Address:"+street1+','+numberOfHouse1);
        text.add("Total lodgers:"+lodgersInFirstHouse );
        text.add("Total Area:"+ squareInFirstHouse);
        text.add("Second House");
        text.add("Address:"+street2+','+numberOfHouse2);
        text.add("Total lodgers:"+ lodgersInSecondHouse);
        text.add("Total Area:"+ squareInSecondHouse);
        text.add("Result:");
        text.add("Total Lodgers :"+((lodgersInFirstHouse==lodgersInSecondHouse)?"the same lodgers in houses":
                (lodgersInFirstHouse>lodgersInSecondHouse ?"In first House is more lodgers":"In second House is more lodgers"))
                );
        text.add("Total Area :"+((squareInFirstHouse==squareInSecondHouse)?"the same squares in houses":
                (squareInFirstHouse>squareInSecondHouse ?"In first House is more square":"In second House is more square"))
        );
        model.addAttribute("text",text);
        return "compareHouses-3-step";
    }

}
