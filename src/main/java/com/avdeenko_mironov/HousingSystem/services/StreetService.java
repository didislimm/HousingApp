package com.avdeenko_mironov.HousingSystem.services;

import com.avdeenko_mironov.HousingSystem.model.Street;
import com.avdeenko_mironov.HousingSystem.model.repo.StreetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class StreetService {

    private final StreetRepository streetRepository;

    public List<Street> getAllStreets(){
        return  streetRepository.findAll();
    }
}
