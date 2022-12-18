package com.avdeenko_mironov.HousingSystem.controllers;

import com.avdeenko_mironov.HousingSystem.model.repo.FlatRepository;
import com.avdeenko_mironov.HousingSystem.model.repo.HouseRepository;
import com.avdeenko_mironov.HousingSystem.services.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

  private final HouseService houseService;

    @RequestMapping( value = "/control",method = RequestMethod.POST)
    public String defineTypeOfMethod(@ModelAttribute("street") String street,
                                     @ModelAttribute("type") String type, Model model){
        List<Integer> numbersOfHouses=houseService.findHousesByStreet(street);
        model.addAttribute("numbers", numbersOfHouses);
        return type;
    }
}
