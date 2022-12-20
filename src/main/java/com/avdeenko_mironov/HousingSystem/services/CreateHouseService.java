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

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateHouseService {

    private final StreetRepository streetRepository;
    private final HouseRepository houseRepository;
    private final FloorRepository floorRepository;
    private final FlatRepository flatRepository;

    private final HouseService houseService;
    private final FloorService floorService;

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
        int streetId= houseService.getIdStreetByName(street);
        house.setStreetId(streetId);
        house.setNumberOfHouse(numberOfHouse);
        return house;
    }

    public void saveHouse(House house){
        List<Floor> floors=house.getFloors();
        houseRepository.save(house);
        int houseId=house.getId();
        int numberOfFloor=0;
        for (Floor floor:floors){
            numberOfFloor++;
            floor.setHouseId(houseId);
            List<Flat> flats=floor.getFlats();
            floorRepository.save(floor);
            int floorId=floorService.getFloor(numberOfFloor,houseId).getId();
            for (Flat flat:flats){
                flat.setFloorId(floorId);
                flatRepository.save(flat);
            }
        }

    }

}
