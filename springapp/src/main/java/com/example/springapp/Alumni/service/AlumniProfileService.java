package com.example.springapp.Alumni.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.Alumni.model.AlumniProfile;
import com.example.springapp.Alumni.repository.AlumniProfileRepository;

@Service
public class AlumniProfileService {

    @Autowired
    private AlumniProfileRepository repository;

    // Create or Update Profile
    public AlumniProfile saveProfile(AlumniProfile profile) {
        return repository.save(profile);
    }

    // Get Profile by UserId
    public AlumniProfile getProfileByUserId(Long userId) {
        return repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    // Public View with Privacy Control
    public AlumniProfile getPublicProfile(Long userId) {

        AlumniProfile profile = getProfileByUserId(userId);

        if ("PRIVATE".equalsIgnoreCase(profile.getContactVisibility())) {
            profile.setEmail(null);
            profile.setPhone(null);
        }

        return profile;
    }

    // Delete Profile
    public void deleteProfile(Long userId) {
        Optional<AlumniProfile> profile = repository.findByUserId(userId);
        profile.ifPresent(repository::delete);
    }
}