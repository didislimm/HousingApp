package com.avdeenko_mironov.HousingSystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Flat {
    @Id
    private long id;

    private int  floorId;
    private int numberOfFlat;
    private double squareOfFlat;
    private int numberOfLodger;
    private int numberOfRoom;

    @Transient
    private int numberOfFloor;

    @Override
    public String toString() {
        return System.lineSeparator() + "Flat " + numberOfFlat +
                " Square=" + squareOfFlat +
                " NumberOfPeople=" + numberOfLodger +
                " NumberOfRoom=" + numberOfRoom + "<p>";
    }


    public static final class FlatBuilder {
        private int numberOfFlat;
        private double squareOfFlat;
        private int numberOfLodger;
        private int numberOfRoom;
        private int numberOfFloor;

        private FlatBuilder() {
        }

        public static FlatBuilder aFlat() {
            return new FlatBuilder();
        }

        public FlatBuilder withNumberOfFlat(int numberOfFlat) {
            this.numberOfFlat = numberOfFlat;
            return this;
        }

        public FlatBuilder withSquareOfFlat(double squareOfFlat) {
            this.squareOfFlat = squareOfFlat;
            return this;
        }

        public FlatBuilder withNumberOfLodger(int numberOfLodger) {
            this.numberOfLodger = numberOfLodger;
            return this;
        }

        public FlatBuilder withNumberOfRoom(int numberOfRoom) {
            this.numberOfRoom = numberOfRoom;
            return this;
        }

        public FlatBuilder withNumberOfFloor(int numberOfFloor) {
            this.numberOfFloor = numberOfFloor;
            return this;
        }

        public Flat build() {
            Flat flat = new Flat();
            flat.setNumberOfFlat(numberOfFlat);
            flat.setSquareOfFlat(squareOfFlat);
            flat.setNumberOfLodger(numberOfLodger);
            flat.setNumberOfRoom(numberOfRoom);
            flat.setNumberOfFloor(numberOfFloor);
            return flat;
        }
    }
}