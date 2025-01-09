package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SchoolInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String organization;
    private String projectUrl;
    private String email;
    private String contactNumber;

    private boolean inSchoolRecording;
    private boolean outsideRecording;
    private boolean agencyCollaboration;
    private boolean greenScreen;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public boolean isInSchoolRecording() {
        return inSchoolRecording;
    }

    public void setInSchoolRecording(boolean inSchoolRecording) {
        this.inSchoolRecording = inSchoolRecording;
    }

    public boolean isOutsideRecording() {
        return outsideRecording;
    }

    public void setOutsideRecording(boolean outsideRecording) {
        this.outsideRecording = outsideRecording;
    }

    public boolean isAgencyCollaboration() {
        return agencyCollaboration;
    }

    public void setAgencyCollaboration(boolean agencyCollaboration) {
        this.agencyCollaboration = agencyCollaboration;
    }

    public boolean isGreenScreen() {
        return greenScreen;
    }

    public void setGreenScreen(boolean greenScreen) {
        this.greenScreen = greenScreen;
    }
}
