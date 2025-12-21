package com.example.demo.location.service.serviceimpl;

import com.example.demo.location.dto.requestdto.LocationRequestDto;
import com.example.demo.location.dto.responsedto.LocationResponseDto;
import com.example.demo.location.entity.Location;
import com.example.demo.location.repository.LocationRepository;
import com.example.demo.location.service.LocationService;
import com.example.demo.member.dto.responsedto.MemberResponseDto;
import com.example.demo.member.entity.Member;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LocationServiceImpl  implements LocationService {

    private static final Logger logger= LoggerFactory.getLogger(LocationServiceImpl.class);

    private final LocationRepository locationRepository;

    //Add Location
    @Override
    public Location addLocation(LocationRequestDto locationRequestDto) {

        logger.info("Creating Location with Name {}", locationRequestDto.getLocationName());

        Location location = new Location();
        BeanUtils.copyProperties(locationRequestDto,location);
        location.setLocid("LOC" + UUID.randomUUID().toString().substring(0, 2));
        Location save = locationRepository.save(location);
        return save;
    }

    // Get all locations
    @Override
    public List<LocationResponseDto> getAllLocations() {
        return locationRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    //Get Location By Id
    @Override
    public LocationResponseDto getLocationById(Integer id) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new RuntimeException("Location not found with ID: " + id));
        return mapToDto(location);
    }

    //Update Location By Id
    @Override
    public LocationResponseDto updateLocationById(Integer id, LocationRequestDto dto) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new RuntimeException("Location not found with ID: " + id));
        BeanUtils.copyProperties(dto, location);
        Location updated = locationRepository.save(location);
        return mapToDto(updated);

    }

    //Delete Location By id
    @Override
    public void deleteLocationById(Integer id) {
        if (!locationRepository.existsById(id)) {
            throw new RuntimeException("Location not found with ID: " + id);
        }
        locationRepository.deleteById(id);
    }

    //Helper method Entity to DTO
    private LocationResponseDto mapToDto(Location location) {
        LocationResponseDto dto = new LocationResponseDto();
        BeanUtils.copyProperties(location, dto);
        return dto;
    }

}
