package com.example.demo.feedback.controller;

import com.example.demo.feedback.dto.requestdto.FeedbackRequestDto;
import com.example.demo.feedback.dto.responsedto.FeedbackResponseDto;
import com.example.demo.feedback.entity.Feedback;
import com.example.demo.feedback.service.FeedbackService;
import com.example.demo.generic.response.commonResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/feedback")

public class FeedbackController {
    private final FeedbackService feedbackService;

    //Add feedback
    @PostMapping("/addFeedback")
    public String addFeedback(@Valid @RequestBody FeedbackRequestDto feedbackRequestDto){
        Feedback feedback=feedbackService.addFeedback(feedbackRequestDto);
        return "Feedback added:"+feedback.getId();
    }

    //Get All feeedbacks
    @GetMapping("/getAllFeedbacks")
    public commonResponse getAllFeedbacks(){
        List<FeedbackResponseDto> feedbacks = feedbackService.getAllFeedbacks();
        return new commonResponse(false, "All feedbacks fetched successfully", feedbacks);
    }


    //Get Feedbacks by Location name
    @GetMapping("/getFeedbacksByLocation/{locationName}")
    public commonResponse getFeedbacksByLocation(@PathVariable("locationName") String locationName){
        List<FeedbackResponseDto> feedbacks = feedbackService.getFeedbacksByLocationName(locationName);
        return new commonResponse(false, "Feedbacks for location fetched successfully", feedbacks);
    }


    //Get Feedbacks by Package Name
    @GetMapping("/getFeedbacksByPackage/{packageName}")
    public commonResponse getFeedbacksByPackage(@PathVariable("packageName") String packageName){
        List<FeedbackResponseDto> feedbacks = feedbackService.getFeedbacksByPackageName(packageName);
        return new commonResponse(false, "Feedbacks for package fetched successfully", feedbacks);
    }

}
