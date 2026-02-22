package com.example.springapp.Alumni.service;

import org.springframework.data.jpa.domain.Specification;
import com.example.springapp.Alumni.model.AlumniProfile;

public class AlumniSpecification {

    public static Specification<AlumniProfile> search(
            String name,
            String batch,
            String company,
            String skills) {

        return (root, query, criteriaBuilder) -> {

            var predicate = criteriaBuilder.conjunction();

            if (name != null && !name.isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + name.toLowerCase() + "%"));
            }

            if (batch != null && !batch.isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("batch"), batch));
            }

            if (company != null && !company.isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("company")),
                                "%" + company.toLowerCase() + "%"));
            }

            if (skills != null && !skills.isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("skills")),
                                "%" + skills.toLowerCase() + "%"));
            }

            return predicate;
        };
    }
}