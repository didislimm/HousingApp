package com.avdeenko_mironov.HousingSystem.services;

import com.avdeenko_mironov.HousingSystem.model.House;
import com.avdeenko_mironov.HousingSystem.model.Street;
import com.avdeenko_mironov.HousingSystem.model.repo.HouseRepository;
import com.avdeenko_mironov.HousingSystem.model.repo.StreetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;
    private final StreetRepository streetRepository;

    public int getIdByName(String street){
        List<Street> streets=streetRepository.findAll().stream()
       .filter(c->c.getName().equals(street)).collect(Collectors.toList());
        return streets.get(0).getId();
    }

    public List<House> getHousesByStreetId(int streetId){
        return houseRepository.findAll().stream()
                .filter(c->c.getStreetId()==streetId )
                .collect(Collectors.toList());
    }



     public List<Integer> findHousesByStreet(String street) {
         int idStreet = 0;
         List<Integer> numbersOfHouses=new ArrayList<>();
         idStreet = getIdByName(street);
         List <House> houses= getHousesByStreetId(idStreet);
         for (int i=0;i<houses.size();i++){
             numbersOfHouses.add(i,houses.get(i).getNumberOfHouse());
         }
         return numbersOfHouses;
     }
}
