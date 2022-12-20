package com.avdeenko_mironov.HousingSystem.model;

import com.avdeenko_mironov.HousingSystem.model.repo.FloorRepository;
import com.avdeenko_mironov.HousingSystem.services.CreateHouseService;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.avdeenko_mironov.HousingSystem.services.CreateHouseService.createRandomFloor;

@Entity
@Getter
@Setter
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Transient
    private List<Floor> floors = new ArrayList<>();

    private int streetId;
    private int numberOfHouse;
    private int valueOfFloors;

    public static final class HouseBuilder {
        private List<Floor> floors = new ArrayList<>();
        private int numberOfHouse;
        private int valueOfFloors;

        private HouseBuilder() {
        }

        public static HouseBuilder aHouse() {
            return new HouseBuilder();
        }

        public HouseBuilder withFloors(List<Floor> floors) {
            this.floors = floors;
            return this;
        }

        public HouseBuilder withValueOfFloors(int valueOfFloors) {
            this.valueOfFloors = valueOfFloors;
            return this;
        }

        public HouseBuilder but(Double[] squareOfFlats, int numberOfFloors) {
            List<Floor> floorList = new ArrayList<>();
            for (int i = 0; i < numberOfFloors; i++) {
                floorList.add( createRandomFloor(squareOfFlats));
            }
            return aHouse().withFloors(floorList)
                    .withValueOfFloors(numberOfFloors);
        }

        public House build() {
            House house = new House();
            house.setFloors(floors);
            house.setNumberOfHouse(numberOfHouse);
            house.setValueOfFloors(valueOfFloors);
            return house;
        }
    }
}

