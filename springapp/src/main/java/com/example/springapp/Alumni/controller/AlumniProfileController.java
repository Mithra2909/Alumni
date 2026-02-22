package com.example.springapp.Alumni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.springapp.Alumni.model.AlumniProfile;
import com.example.springapp.Alumni.service.AlumniProfileService;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class AlumniProfileController {

    @Autowired
    private AlumniProfileService service;

    // Create or Update Profile
    @PostMapping
    public AlumniProfile createOrUpdateProfile(@RequestBody AlumniProfile profile) {
        return service.saveProfile(profile);
    }

    // View Own Profile
    @GetMapping("/{userId}")
    public AlumniProfile getProfile(@PathVariable Long userId) {
        return service.getProfileByUserId(userId);
    }

    // View Public Profile
    @GetMapping("/public/{userId}")
    public AlumniProfile getPublicProfile(@PathVariable Long userId) {
        return service.getPublicProfile(userId);
    }

    // Delete Profile
    @DeleteMapping("/{userId}")
    public String deleteProfile(@PathVariable Long userId) {
        service.deleteProfile(userId);
        return "Profile deleted successfully";
    }
}