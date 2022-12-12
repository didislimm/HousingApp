package com.avdeenko_mironov.HousingSystem.controllers;

import com.avdeenko_mironov.HousingSystem.model.Street;
import com.avdeenko_mironov.HousingSystem.model.repo.StreetRepository;
import com.avdeenko_mironov.HousingSystem.services.StreetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class InputStreetController {

    private final StreetService streetService;

    @GetMapping("/streets")
    public String outputStreets( Model model,@RequestParam String type) {
        List<Street> streets=streetService.getAllStreets();
        model.addAttribute("streets",streets);
        model.addAttribute("type",type);
        return "streets";
    }
}
