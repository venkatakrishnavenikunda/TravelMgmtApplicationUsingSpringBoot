package com.example.demo.travelpackage.service.serviceimpl;

import com.example.demo.booking.repository.BookingRepository;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.travelpackage.dto.requestdto.TravelPackageRequestDto;
import com.example.demo.travelpackage.dto.responsedto.TravelPackageResponseDto;
import com.example.demo.travelpackage.entity.TravelPackage;
import com.example.demo.travelpackage.repository.TravelPackageRepository;
import com.example.demo.travelpackage.service.TravelPackageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TravelPackageServiceImpl implements TravelPackageService {
    private final TravelPackageRepository travelPackageRepository;

    private final MemberRepository memberRepository;

    private final BookingRepository bookingRepository;

    //Add Package Service
    @Override
    public void addTravelPackage(TravelPackageRequestDto travelPackageRequestDto) {

        TravelPackage tp=new TravelPackage();
        BeanUtils.copyProperties(travelPackageRequestDto,tp);
        travelPackageRepository.save(tp);
    }

    //Get All Packages Service
    @Override
    public List<TravelPackageResponseDto> getAllPackages() {
        List<TravelPackage> travelPackageList = travelPackageRepository.findAll();
        List<TravelPackageResponseDto> travelPackageResponseDtoList = new ArrayList<>();
        travelPackageList.stream().forEach(travelPackage -> {
            TravelPackageResponseDto travelPackageResponseDto = new TravelPackageResponseDto();
            BeanUtils.copyProperties(travelPackage,travelPackageResponseDto);
            travelPackageResponseDtoList.add(travelPackageResponseDto);
        });
        return travelPackageResponseDtoList;
    }

    //Get Package By Id Service Implementation
    @Override
    public TravelPackageResponseDto getPackageById(Integer id) {
        TravelPackage travelPackage = travelPackageRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not Found"));
        TravelPackageResponseDto travelPackageResponseDto = new TravelPackageResponseDto();
        BeanUtils.copyProperties(travelPackage,travelPackageResponseDto);
        return travelPackageResponseDto;
    }


    //Get Packages By Location Name
    @Override
    public List<TravelPackageResponseDto> getPackageByLocName(String locationName){
        List<TravelPackage> packageByLocName = travelPackageRepository.findByLocation_LocationName(locationName);
        List<TravelPackageResponseDto> travelPackageResponseDtoList=new ArrayList<>();
        packageByLocName.stream().forEach(travelPackage -> {
            TravelPackageResponseDto travelPackageResponseDto = new TravelPackageResponseDto();
            BeanUtils.copyProperties(travelPackage,travelPackageResponseDto);
            travelPackageResponseDtoList.add(travelPackageResponseDto);
        });
        return travelPackageResponseDtoList;
    }


    //Get Package By Location ID Sevice Implementation
    @Override
    public List<TravelPackageResponseDto> getPackageByLocId(Integer locationId) {
        List<TravelPackage> byLocationId = travelPackageRepository.findByLocation_Id(locationId);
        List<TravelPackageResponseDto> travelPackageResponseDtoList=new ArrayList<>();
        byLocationId.stream().forEach(travelPackage -> {
            TravelPackageResponseDto travelPackageResponseDto=new TravelPackageResponseDto();
            BeanUtils.copyProperties(travelPackage,travelPackageResponseDto);
            travelPackageResponseDtoList.add(travelPackageResponseDto);
        });
        return travelPackageResponseDtoList;
    }


    //Update Package By Package Id Sevice Implementation
    @Override
    public TravelPackage updatePackageById(Integer packId, TravelPackageRequestDto dto) {
        TravelPackage travelPackage = travelPackageRepository.findById(packId).orElseThrow(() -> new RuntimeException("Package not Found with Id:" + packId));
        BeanUtils.copyProperties(dto,travelPackage);
        TravelPackage updatedPackage = travelPackageRepository.save(travelPackage);
        return updatedPackage;
    }

    //Delete Package by Package ID Sevice Implementation
    @Override
    public void deletePackageById(Integer id) {
        TravelPackage travelPackage = travelPackageRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found with ID:" + id));
        travelPackageRepository.delete(travelPackage);
    }

}