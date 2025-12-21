package com.example.demo.feedback.service.serviceimpl;

import com.example.demo.feedback.dto.requestdto.FeedbackRequestDto;
import com.example.demo.feedback.dto.responsedto.FeedbackResponseDto;
import com.example.demo.feedback.entity.Feedback;
import com.example.demo.feedback.repository.FeedbackRepository;
import com.example.demo.feedback.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Override
    public Feedback addFeedback(FeedbackRequestDto feedbackRequestDto) {
        Feedback feedback = new Feedback();
        BeanUtils.copyProperties(feedbackRequestDto, feedback);
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<FeedbackResponseDto> getAllFeedbacks() {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        List<FeedbackResponseDto> dtoList = new ArrayList<>();
        feedbackList.forEach(feedback -> {
            FeedbackResponseDto dto = new FeedbackResponseDto();
            BeanUtils.copyProperties(feedback, dto);
            dtoList.add(dto);
        });
        return dtoList;
        /*return feedbackRepository.findAll().stream().map(this::mapToResponseDto).toList();*/
    }

    @Override
    public List<FeedbackResponseDto> getFeedbacksByLocationName(String locationName) {
        List<Feedback> feedbackList = feedbackRepository.findByTravelPackage_Member_Locations_LocationName(locationName);
        List<FeedbackResponseDto> dtoList = new ArrayList<>();
        feedbackList.forEach(feedback -> {
            FeedbackResponseDto dto = new FeedbackResponseDto();
            BeanUtils.copyProperties(feedback, dto);
            dtoList.add(dto);});
        return dtoList;
    }

    @Override
    public List<FeedbackResponseDto> getFeedbacksByPackageName(String packageName) {
        List<Feedback> feedbackList = feedbackRepository.findByTravelPackage_PackageName(packageName);
        List<FeedbackResponseDto> dtoList = new ArrayList<>();
        feedbackList.forEach(feedback -> {
            FeedbackResponseDto dto = new FeedbackResponseDto();
            BeanUtils.copyProperties(feedback, dto);
            dtoList.add(dto);
        });
        return dtoList;
    }


    //Helper
    private FeedbackResponseDto mapToResponseDto(Feedback feedback) {
        FeedbackResponseDto dto = new FeedbackResponseDto();
        dto.setId(feedback.getId());
        dto.setFeedback(feedback.getFeedback());
        return dto;
    }

}
