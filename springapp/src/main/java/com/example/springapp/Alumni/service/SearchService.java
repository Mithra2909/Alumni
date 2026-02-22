package com.example.springapp.Alumni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import com.example.springapp.Alumni.model.AlumniProfile;
import com.example.springapp.Alumni.repository.AlumniProfileRepository;

@Service
public class SearchService {

    @Autowired
    private AlumniProfileRepository repository;

    public Page<AlumniProfile> searchAlumni(
            String name,
            String batch,
            String company,
            String skills,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("name").ascending());

        return repository.findAll(
                AlumniSpecification.search(name, batch, company, skills),
                pageable);
    }
}