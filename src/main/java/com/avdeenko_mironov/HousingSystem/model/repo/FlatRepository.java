package com.avdeenko_mironov.HousingSystem.model.repo;

import com.avdeenko_mironov.HousingSystem.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlatRepository extends JpaRepository<Flat, Long> {
}
