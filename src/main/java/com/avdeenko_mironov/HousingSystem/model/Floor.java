package com.avdeenko_mironov.HousingSystem.model;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int houseId;
    private int numberOfFloor;
    private int numberOfFlats;
    private int numberOfHouse;

//    @Override
//    public String toString() {
//
//        StringBuilder flatsToString = new StringBuilder();
//        for (Flat flat : flats) {
//            flatsToString.append(flat.toString());
//        }
//        return numberOfFloor + " Floor " +
//                " flats:<p>" + flatsToString;
//    }
}
