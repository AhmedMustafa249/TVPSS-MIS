package com.example.service;

import com.example.model.SchoolInformation;
import com.example.repository.SchoolInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolInformationService {

    private final SchoolInformationRepository repository;

    public SchoolInformationService(SchoolInformationRepository repository) {
        this.repository = repository;
    }

    public List<SchoolInformation> getAllRequests() {
        return repository.findAll();
    }

    public SchoolInformation createRequest(SchoolInformation request) {
        return repository.save(request);
    }

    public void deleteRequest(Long id) {
        repository.deleteById(id);
    }
}
