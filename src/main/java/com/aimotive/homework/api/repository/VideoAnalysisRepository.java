package com.aimotive.homework.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aimotive.homework.api.model.VideoAnalysis;

public interface VideoAnalysisRepository extends JpaRepository<VideoAnalysis, String>{
}