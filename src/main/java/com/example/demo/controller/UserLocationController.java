package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserLocation;
import com.example.demo.service.UserLocationService;

@RestController
@RequestMapping("/api")
public class UserLocationController {

	@Autowired
    private UserLocationService userLocationService;

    @PostMapping("/create_data")
    public ResponseEntity<UserLocation> createData(@RequestBody UserLocation userLocation, @RequestHeader(name = "Role") String role) {
        if (role.equals("ADMIN")) {
            UserLocation createdLocation = userLocationService.createLocation(userLocation);
            return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PatchMapping("/update_data/{id}")
    public ResponseEntity<UserLocation> updateData(@PathVariable Long id, @RequestBody UserLocation userLocation,
                                                   @RequestHeader(name = "Role") String role) {
        if (role.equals("ADMIN")) {
            userLocation.setId(id);
            UserLocation updatedLocation = userLocationService.updateLocation(userLocation);
            return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/get_users/{n}")
    public ResponseEntity<List<UserLocation>> getNearestUsers(@PathVariable int n,
                                                              @RequestHeader(name = "Role") String role) {
        if (role.equals("READER")) {
            List<UserLocation> nearestUsers = userLocationService.getNearestUsers(n);
            return new ResponseEntity<>(nearestUsers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
}
