package com.avdeenko_mironov.HousingSystem.services;

import com.avdeenko_mironov.HousingSystem.model.House;
import com.avdeenko_mironov.HousingSystem.model.Street;
import com.avdeenko_mironov.HousingSystem.model.repo.HouseRepository;
import com.avdeenko_mironov.HousingSystem.model.repo.StreetRepository;
import com.avdeenko_mironov.HousingSystem.model.repo.impl.StreetRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;
    private final StreetRepositoryImpl streetRepository;

     public List<House> findHousesByStreet(String street){
         streetRepository.getIdByName();
     }
}
