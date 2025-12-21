package com.example.demo.travelpackage.repository;

import com.example.demo.travelpackage.entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelPackageRepository extends JpaRepository<TravelPackage, Integer> {
    List<TravelPackage> findByLocation_Id(Integer locationId);
    List<TravelPackage> findByLocation_LocationName(String locationName);
}
