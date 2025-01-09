package com.example.controller;

import com.example.model.SchoolInformation;
import com.example.service.SchoolInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recording-requests")
public class SchoolInformationController {

    private final SchoolInformationService service;

    public SchoolInformationController(SchoolInformationService service) {
        this.service = service;
    }

    @GetMapping
    public List<SchoolInformation> getAllRequests() {
        return service.getAllRequests();
    }

    @PostMapping
    public SchoolInformation createRequest(@RequestBody SchoolInformation request) {
        return service.createRequest(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        service.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}
