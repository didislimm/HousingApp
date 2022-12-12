package com.avdeenko_mironov.HousingSystem.model.repo;

import com.avdeenko_mironov.HousingSystem.model.Street;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetRepository extends JpaRepository<Street, Integer> {
}
