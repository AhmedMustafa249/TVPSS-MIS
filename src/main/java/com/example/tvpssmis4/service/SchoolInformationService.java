package com.example.tvpssmis4.service;

import com.example.tvpssmis4.model.SchoolInformation;
import com.example.tvpssmis4.repository.SchoolInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolInformationService {

    private final SchoolInformationRepository repository;

    public SchoolInformationService(SchoolInformationRepository repository) {
        this.repository = repository;
    }

    public List<SchoolInformation> getAllSchools() {
        return repository.findAll();
    }

    public SchoolInformation getSchoolById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public SchoolInformation createOrUpdateSchool(SchoolInformation school) {
        return repository.save(school);
    }

    public void deleteSchool(Long id) {
        repository.deleteById(id);
    }

    public List<SchoolInformation> getPendingVerificationSchools() {
        return repository.findAll().stream()
                .filter(school -> !school.isVerified())
                .toList();
    }
}
