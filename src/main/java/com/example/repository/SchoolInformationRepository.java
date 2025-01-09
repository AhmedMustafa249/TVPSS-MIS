package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.SchoolInformation;

public interface SchoolInformationRepository extends JpaRepository<SchoolInformation, Long> {
}
