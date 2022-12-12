package com.avdeenko_mironov.HousingSystem.model.repo;

import com.avdeenko_mironov.HousingSystem.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HouseRepository extends JpaRepository<House, Integer> {
}
