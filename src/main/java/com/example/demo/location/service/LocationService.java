package com.example.demo.location.service;

import com.example.demo.location.dto.requestdto.LocationRequestDto;
import com.example.demo.location.dto.responsedto.LocationResponseDto;
import com.example.demo.location.entity.Location;

import java.util.List;

public interface LocationService {
    Location addLocation(LocationRequestDto locationRequestDto);

    List<LocationResponseDto> getAllLocations();

    LocationResponseDto getLocationById(Integer id);

    LocationResponseDto updateLocationById(Integer id, LocationRequestDto dto);

    void deleteLocationById(Integer id);
}
