package com.example.demo.feedback.service;

import com.example.demo.feedback.dto.requestdto.FeedbackRequestDto;
import com.example.demo.feedback.dto.responsedto.FeedbackResponseDto;
import com.example.demo.feedback.entity.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback addFeedback(FeedbackRequestDto feedbackRequestDto);

    List<FeedbackResponseDto> getAllFeedbacks();

    List<FeedbackResponseDto> getFeedbacksByLocationName(String locationName);

    List<FeedbackResponseDto> getFeedbacksByPackageName(String packageName);
}
