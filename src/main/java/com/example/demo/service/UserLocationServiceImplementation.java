package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserLocation;
import com.example.demo.repository.UserLocationRepository;

@Service
public class UserLocationServiceImplementation implements UserLocationService {
	
	@Autowired
	private UserLocationRepository userLocationRepository;

	@Override
	public UserLocation createLocation(UserLocation userLocation) {
		// TODO Auto-generated method stub
		return userLocationRepository.save(userLocation);
	}

	@Override
	public UserLocation updateLocation(UserLocation userLocation) {
		// TODO Auto-generated method stub
		return userLocationRepository.save(userLocation);
	}

	@Override
	public List<UserLocation> getNearestUsers(int n) {
		double latitude = 0.0; // Latitude of (0, 0)
	    double longitude = 0.0; // Longitude of (0, 0)

	    PageRequest pageable = PageRequest.of(0, n); // Fetch first N records

	    List<UserLocation> nearestUsers = userLocationRepository.findByExcludedFalseOrderByDistance(latitude, longitude, pageable);

	    return nearestUsers;
	}

}
