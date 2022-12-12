package com.avdeenko_mironov.HousingSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class House {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private int streetId;
    private int numberOfHouse;
    private int valueOfFloors;
    private String nameOfStreet;



//    @Override
//    public String toString() {
//        StringBuilder floorsToString = new StringBuilder();
//        for (Floor floor : floors) {
//            floorsToString.append(floor.toString());
//        }
//        return null;
//    }
}

