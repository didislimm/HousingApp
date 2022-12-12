package com.avdeenko_mironov.HousingSystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int  floorId;
    private int numberOfFlat;
    private double squareOfFlat;
    private int numberOfLodger;
    private int numberOfRoom;
    private int numberOfFloor;

    @Override
    public String toString() {
        return System.lineSeparator() + "Flat " + numberOfFlat +
                " Square=" + squareOfFlat +
                " NumberOfPeople=" + numberOfLodger +
                " NumberOfRoom=" + numberOfRoom + "<p>";
    }
}