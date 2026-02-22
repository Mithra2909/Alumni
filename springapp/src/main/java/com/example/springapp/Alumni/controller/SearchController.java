package com.example.springapp.Alumni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import com.example.springapp.Alumni.model.AlumniProfile;
import com.example.springapp.Alumni.service.SearchService;

@RestController
@RequestMapping("/api/search")
@CrossOrigin("*")
public class SearchController {

    @Autowired
    private SearchService service;

    @GetMapping("/alumni")
    public Page<AlumniProfile> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String batch,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String skills,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return service.searchAlumni(
                name, batch, company, skills, page, size);
    }
}