package com.example.tvpssmis4.service;

import com.example.tvpssmis4.model.SchoolInformation;
import com.example.tvpssmis4.repository.SchoolInformationRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public SchoolInformation updateSchool(SchoolInformation school) {
        SchoolInformation existingSchool = repository.findById(school.getId())
                .orElseThrow(() -> new RuntimeException("School not found"));

        existingSchool.setSchoolName(school.getSchoolName());
        existingSchool.setSchoolCode(school.getSchoolCode());
        existingSchool.setAddress(school.getAddress());
        existingSchool.setBrandName(school.getBrandName());
        existingSchool.setYoutubeChannelLink(school.getYoutubeChannelLink());
        existingSchool.setEmail(school.getEmail());
        existingSchool.setContactInformation(school.getContactInformation());
        existingSchool.setPersonInCharge(school.getPersonInCharge());
        existingSchool.setBrandName(school.getBrandName());
        existingSchool.setVerified(school.isVerified());
        existingSchool.setVersion(school.getVersion());

        return repository.save(existingSchool);
    }

    public void deleteSchool(Long id) {
        repository.deleteById(id);
    }

    public List<SchoolInformation> getPendingVerificationSchools() {
        return repository.findAll().stream()
                .filter(school -> !school.isVerified())
                .toList();
    }

    public SchoolInformation verifyStatus(Long id) {

        SchoolInformation school = repository.findById(id).orElse(null);
        school.setVerified(true);

        return repository.save(school);
    }

}
