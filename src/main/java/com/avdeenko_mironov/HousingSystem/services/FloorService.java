package com.avdeenko_mironov.HousingSystem.services;

import com.avdeenko_mironov.HousingSystem.model.Flat;
import com.avdeenko_mironov.HousingSystem.model.Floor;
import com.avdeenko_mironov.HousingSystem.model.House;
import com.avdeenko_mironov.HousingSystem.model.repo.FlatRepository;
import com.avdeenko_mironov.HousingSystem.model.repo.FloorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class FloorService {

    private final FloorRepository floorRepository;
    private final FlatRepository flatRepository;

    public double getCountingOfSquare(Floor floor) {
        return floor.getFlats()
                .stream()
                .flatMapToDouble(flat -> DoubleStream.of(flat.getSquareOfFlat()))
                .sum();
    }

    public int getCountingOfLodger(Floor floor) {
        return floor.getFlats()
                .stream()
                .flatMapToInt(flat -> IntStream.of(flat.getNumberOfLodger()))
                .sum();
    }

    public int getCountingOfRooms(Floor floor) {
        return floor.getFlats()
                .stream()
                .flatMapToInt(flat -> IntStream.of(flat.getNumberOfRoom()))
                .sum();
    }

    public Floor getFloor(int numberOfFloor, int houseId){
        List<Floor> floor= floorRepository.findAll()
                .stream()
                .filter(c-> c.getNumberOfFloor()==numberOfFloor && c.getHouseId()==houseId)
                .collect(Collectors.toList());
        return floor.get(0);
    }
}
