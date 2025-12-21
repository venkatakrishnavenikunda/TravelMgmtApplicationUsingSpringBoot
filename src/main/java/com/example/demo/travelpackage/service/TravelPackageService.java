package com.example.demo.travelpackage.service;

import com.example.demo.travelpackage.dto.requestdto.TravelPackageRequestDto;
import com.example.demo.travelpackage.dto.responsedto.TravelPackageResponseDto;
import com.example.demo.travelpackage.entity.TravelPackage;

import java.util.List;

public interface TravelPackageService {
    void addTravelPackage(TravelPackageRequestDto travelPackageRequestDto);

    List<TravelPackageResponseDto> getAllPackages();

    TravelPackageResponseDto getPackageById(Integer id);

    List<TravelPackageResponseDto> getPackageByLocId(Integer locationId);

    List<TravelPackageResponseDto> getPackageByLocName(String locationName);
    TravelPackage updatePackageById(Integer packId, TravelPackageRequestDto travelPackageRequestDto);

    void deletePackageById(Integer id);

}





