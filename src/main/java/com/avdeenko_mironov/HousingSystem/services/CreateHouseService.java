package com.avdeenko_mironov.HousingSystem.services;

import com.avdeenko_mironov.HousingSystem.model.Flat;
import com.avdeenko_mironov.HousingSystem.model.Floor;
import com.avdeenko_mironov.HousingSystem.model.House;
import com.avdeenko_mironov.HousingSystem.model.repo.FlatRepository;
import com.avdeenko_mironov.HousingSystem.model.repo.FloorRepository;
import com.avdeenko_mironov.HousingSystem.model.repo.HouseRepository;
import com.avdeenko_mironov.HousingSystem.model.repo.StreetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CreateHouseService {

    private final HouseRepository houseRepository;
    private final FloorRepository floorRepository;
    private final FlatRepository flatRepository;
    private final HouseService houseService;

    private static int flatNumber = 0;
    private static int floorNumber = 0;

    private static int flatId=0;
    private static int floorId=0;
    private static int houseId=0;

    public static Double getRandomDoubleInRange(int min, int max) {
        max -= min;
        return (Math.random() * ++max) + min;
    }

    public static Flat createRandomFlat(double squareOfFlat) {
        int randomNumberOfRoom = getRandomDoubleInRange(1, 4).intValue();
        int randomNumberOfLodger = getRandomDoubleInRange(0, 6).intValue();
        flatNumber++;
        Flat flat=Flat.FlatBuilder.aFlat()
                .withNumberOfFlat(flatNumber)
                .withNumberOfLodger(randomNumberOfLodger)
                .withNumberOfRoom(randomNumberOfRoom)
                .withSquareOfFlat(squareOfFlat)
                .build();
        return flat;
    }
    public  static Floor  createRandomFloor(Double[] squareOfFlats) {
        floorNumber++;
        Floor floor=Floor.FloorBuilder.aFloor().but(squareOfFlats, floorNumber).build();
        for (Flat flat:floor.getFlats()){
            flat.setFloorId(floor.getId());
        }

        return floor;
    }

    public  House createRandomHouse(Double[] squareOfFlats, int valueOfFloors, String street, int numberOfHouse) {
        House house = House.HouseBuilder.aHouse().but(squareOfFlats, valueOfFloors).build();
        flatNumber = 0;
        floorNumber = 0;
        houseId++;
        house.setId(houseId);
        int streetId= houseService.getIdByName(street);
        house.setStreetId(streetId);
        for (Floor floor:house.getFloors()){
            floor.setHouseId(house.getId());
        }
        house.setNumberOfHouse(numberOfHouse);
        houseRepository.save(house);
        for (Floor floor:house.getFloors()){
            floorId++;
            floor.setId(floorId);
            floor.setHouseId(houseId);
            floorRepository.save(floor);
            for (Flat flat:floor.getFlats()){
                flatId++;
                flat.setId(flatId);
                flat.setFloorId(floorId);
                flatRepository.save(flat);
            }
        }
        return house;
    }


}
