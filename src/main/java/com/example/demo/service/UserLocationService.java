package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.UserLocation;

public interface UserLocationService {

	public UserLocation createLocation(UserLocation userLocation);
	public UserLocation updateLocation(UserLocation userLocation);
	public List<UserLocation> getNearestUsers(int n);
}
