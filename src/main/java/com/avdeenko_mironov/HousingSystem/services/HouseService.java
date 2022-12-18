package com.avdeenko_mironov.HousingSystem.services;

import com.avdeenko_mironov.HousingSystem.model.Flat;
import com.avdeenko_mironov.HousingSystem.model.Floor;
import com.avdeenko_mironov.HousingSystem.model.House;
import com.avdeenko_mironov.HousingSystem.model.Street;
import com.avdeenko_mironov.HousingSystem.model.repo.FlatRepository;
import com.avdeenko_mironov.HousingSystem.model.repo.FloorRepository;
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

    private final FlatRepository flatRepository;
    private final FloorRepository floorRepository;
    private final HouseRepository houseRepository;
    private final StreetRepository streetRepository;

    private final FloorService floorService;

    public int getIdStreetByName(String street){
        List<Street> streets=streetRepository.findAll().stream()
       .filter(c->c.getName().equals(street)).collect(Collectors.toList());
        return streets.get(0).getId();
    }

    public List<House> getHousesByStreetId(int streetId){
        return houseRepository.findAll().stream()
                .filter(c->c.getStreetId()==streetId )
                .collect(Collectors.toList());
    }

    public int getValueOfFlatsInHouse(int numberOfHouse,String street) {
        House house = getHouse(numberOfHouse,street);
        int valueOfFlats = 0;
        for (Floor floor : house.getFloors()) {
            valueOfFlats += floor.getNumberOfFlats();
        }
        return valueOfFlats;
    }

    public double getTotalAreaOfHouse(int numberOfHouse,String street) {
        House house = getHouse(numberOfHouse,street);
        double totalArea = 0;
        for (Floor floor : house.getFloors()) {
            totalArea += floorService.getCountingOfSquare(floor);
        }
        return totalArea;
    }

    public int getTotalLodgersOfHouse(int numberOfHouse,String street) {
        House house = getHouse(numberOfHouse,street);
        int totalLodgers = 0;
        for (Floor floor : house.getFloors()) {
            totalLodgers += floorService.getCountingOfLodger(floor);
        }
        return totalLodgers;
    }

    public void removeHouse(String street, Integer numberOfHouse){
        House house = getHouse(numberOfHouse, street);
        for(Floor floor : house.getFloors()){
            for(Flat flat: floor.getFlats()){
                flatRepository.delete(flat);
            }
            floorRepository.delete(floor);
        }
        houseRepository.delete(house);
    }

     public List<Integer> findHousesByStreet(String street) {
         int idStreet = 0;
         List<Integer> numbersOfHouses=new ArrayList<>();
         idStreet = getIdStreetByName(street);
         List <House> houses= getHousesByStreetId(idStreet);
         for (int i=0;i<houses.size();i++){
             numbersOfHouses.add(i,houses.get(i).getNumberOfHouse());
         }
         return numbersOfHouses;
     }

    public House getHouse(int numberOfHouse,String street){
        int streetId= getIdStreetByName(street);
        List <House> house=houseRepository.findAll()
                .stream()
                .filter(c-> c.getNumberOfHouse()==numberOfHouse && c.getStreetId()==streetId)
                .collect(Collectors.toList());
        int houseId=house.get(0).getId();
        List<Floor> floors=floorRepository.findAll()
                .stream()
                .filter(c-> c.getHouseId()==houseId)
                .collect(Collectors.toList());
        for (Floor floor: floors){
            List<Flat> flats=flatRepository.findAll()
                    .stream()
                    .filter(c-> c.getFloorId()==floor.getId())
                    .collect(Collectors.toList());
            floor.setFlats(flats);
        }
        house.get(0).setFloors(floors);
        return house.get(0);
    }
}
