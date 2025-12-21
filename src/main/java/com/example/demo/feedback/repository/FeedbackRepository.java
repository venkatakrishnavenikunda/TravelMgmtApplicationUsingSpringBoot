package com.example.demo.feedback.repository;

import com.example.demo.feedback.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {

    List<Feedback> findByTravelPackage_Member_Locations_LocationName(String locationName);

    List<Feedback> findByTravelPackage_PackageName(String packageName);

}
