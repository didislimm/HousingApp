package com.avdeenko_mironov.HousingSystem.model;

import com.avdeenko_mironov.HousingSystem.services.CreateHouseService;
import com.avdeenko_mironov.HousingSystem.services.HouseService;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Floor {

    @Id
    private int id;

    @Transient
    private List<Flat> flats = new ArrayList<>();

    private int houseId;
    private int numberOfFloor;
    private int numberOfFlats;

    @Transient
    private int numberOfHouse;

    @Override
    public String toString() {

        StringBuilder flatsToString = new StringBuilder();
        for (Flat flat : flats) {
            flatsToString.append(flat.toString());
        }
        return numberOfFloor + " Floor " +
                " flats:<p>" + flatsToString;
    }

    public static final class FloorBuilder {
        private List<Flat> flats = new ArrayList<>();
        private int numberOfFloor;
        private int numberOfFlats;
        private int numberOfHouse;

        private FloorBuilder() {
        }

        public static FloorBuilder aFloor() {
            return new FloorBuilder();
        }

        public FloorBuilder withFlats(List<Flat> flats) {
            this.flats = flats;
            return this;
        }

        public FloorBuilder withNumberOfFloor(int numberOfFloor) {
            this.numberOfFloor = numberOfFloor;
            return this;
        }

        public FloorBuilder withNumberOfFlats(int numberOfFlats) {
            this.numberOfFlats = numberOfFlats;
            return this;
        }

        public FloorBuilder withNumberOfHouse(int numberOfHouse) {
            this.numberOfHouse = numberOfHouse;
            return this;
        }

        public FloorBuilder but(Double[] squareOfFlats, int numberOfFloor) {
            List<Flat> flatList = new ArrayList<>();
            for (Double squareOfFlat : squareOfFlats) {
                flatList.add(CreateHouseService.createRandomFlat(squareOfFlat));
            }
            return aFloor().withFlats(flatList).withNumberOfFloor(numberOfFloor)
                    .withNumberOfFlats(squareOfFlats.length)
                    .withNumberOfHouse(numberOfHouse);        }

        public Floor build() {
            Floor floor = new Floor();
            floor.setFlats(flats);
            floor.setNumberOfFloor(numberOfFloor);
            floor.setNumberOfFlats(numberOfFlats);
            floor.setNumberOfHouse(numberOfHouse);
            return floor;
        }
    }
}
