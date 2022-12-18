package com.avdeenko_mironov.HousingSystem.services;

import com.avdeenko_mironov.HousingSystem.model.Floor;
import org.springframework.stereotype.Service;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

@Service
public class FloorService {

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
}
