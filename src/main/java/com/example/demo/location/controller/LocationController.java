package com.example.demo.location.controller;
import com.example.demo.generic.response.commonResponse;
import com.example.demo.location.dto.requestdto.LocationRequestDto;
import com.example.demo.location.entity.Location;
import com.example.demo.location.service.LocationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//Member member = memberService.createMember(memberRequestDto);

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/location")
public class LocationController {
    private LocationService locationService;

    //Add location
    @PostMapping("/addLocation")
    public String addLocation(@Valid @RequestBody LocationRequestDto locationRequestDto){
        Location location=locationService.addLocation(locationRequestDto);
        return  location.getLocationName()+" Location added";
    }

    //Get All Locations
    @GetMapping("/getAllLocations")
    public commonResponse getAllLocations() {
        return new commonResponse(false, "Locations fetched successfully", locationService.getAllLocations());
    }

    //Get Location By Location ID
    @GetMapping("/getLocationById/{id}")
    public commonResponse getLocationById(@PathVariable("id") @Positive(message = "Location Id must be positive") Integer id) {
        return new commonResponse(false, "Location fetched successfully", locationService.getLocationById(id));
    }


    //Update Location By Location Id
    @PutMapping("/updateLocationById/{id}")
    public commonResponse updateLocationById(@PathVariable @Positive(message = "Location Id must be positive") Integer id, @Valid @RequestBody LocationRequestDto dto) {
        return new commonResponse(false, "Location updated successfully", locationService.updateLocationById(id, dto));
    }

    //Delete Location By Location Id
    @DeleteMapping("/deleteLocationById/{id}")
    public commonResponse deleteLocationById(@PathVariable @Positive(message = "Location Id must be positive") Integer id) {
        locationService.deleteLocationById(id);
        return new commonResponse(false, "Location deleted successfully", "Deleted");
    }
}
