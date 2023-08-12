package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserLocation;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {

	List<UserLocation> findByExcludedFalseOrderByDistance(double latitude, double longitude, Pageable pageable);
}
