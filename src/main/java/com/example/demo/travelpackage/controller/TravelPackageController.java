package com.example.demo.travelpackage.controller;

import com.example.demo.travelpackage.dto.requestdto.TravelPackageRequestDto;
import com.example.demo.travelpackage.dto.responsedto.TravelPackageResponseDto;
import com.example.demo.travelpackage.entity.TravelPackage;
import com.example.demo.travelpackage.service.TravelPackageService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.demo.generic.response.commonResponse;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/travelpackage")

public class TravelPackageController {
    private final TravelPackageService travelPackageService;


    private TravelPackageResponseDto mapToResponseDto(TravelPackage entity) {
        if (entity == null) return null;
        TravelPackageResponseDto dto = new TravelPackageResponseDto();
        BeanUtils.copyProperties(entity, dto); // copies fields with the same names
        return dto;
    }


    //Add package Controller
    @PostMapping("/addPackage")
    public String  addPackage(@RequestBody TravelPackageRequestDto travelPackageRequestDto){
       travelPackageService.addTravelPackage(travelPackageRequestDto);
        return "Package added successfully:"+ travelPackageRequestDto.getPackageName();
    }


    //Get All Packages
    @GetMapping("/getAllPackages")
    public commonResponse getAllPackages(){
        return new commonResponse(false,"Packages fetched successfully",travelPackageService.getAllPackages());
    }

    //Get Package By Id
    @GetMapping("/getPackageById/{id}")
    public ResponseEntity<commonResponse> getPackageById(@PathVariable @NotBlank(message = "Package ID can't be blank")
                                                         @Min(value = 1, message = "Package ID must be atleast 1")@Positive(message = "Package ID must be Positive") Integer id){
        return ResponseEntity.ok(new commonResponse(false,"Package fetched successfully",travelPackageService.getPackageById(id)));
    }

    //Get Package By Location Id Controller
    @GetMapping("/getPackageByLocId/{locationId}")
    public ResponseEntity<commonResponse> getPackageByLocationId(@PathVariable @NotBlank(message = "Location Id can't be blank") Integer locationId){
        return ResponseEntity.ok(new commonResponse(false,"Package Fetched Successfully",travelPackageService.getPackageByLocId(locationId)));
    }


    //Get Packages by Location name Controller
    @GetMapping("/getPackageByLocName/{locName}")
    public ResponseEntity<commonResponse> getPackageByLocName(@PathVariable @NotBlank(message = "Location name cannot be empty") String locationName){
        return ResponseEntity.ok(new commonResponse(false, "Package deleted successfully", "Deleted"));
    }

    // Update Package By Package ID
    @PutMapping("/updatePackageById/{id}")
    public ResponseEntity<commonResponse> updatePackageById(@PathVariable @Positive(message = "Package Id must be positive") Integer id, @Valid @RequestBody TravelPackageRequestDto dto) {
        TravelPackage updatedEntity = travelPackageService.updatePackageById(id, dto);
        TravelPackageResponseDto responseDto = mapToResponseDto(updatedEntity);
        return ResponseEntity.ok(new commonResponse(false, "Package updated successfully",responseDto));
    }

    //Delete Package By Id
    @DeleteMapping("/deletePackageById/{id}")
    public ResponseEntity<commonResponse> deletePakagebyId(@PathVariable @Positive(message = "Package ID must be positive") Integer Id){
        travelPackageService.deletePackageById(Id);
        return ResponseEntity.ok(new commonResponse(false,"Package Deleted Successfully","Deleted"));
    }

}
