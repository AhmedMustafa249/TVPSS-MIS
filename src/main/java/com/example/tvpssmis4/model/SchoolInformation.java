package com.example.tvpssmis4.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SchoolInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String schoolName;
    private String address;
    private String brandName;
    private String youtubeChannelLink;
    private String email;
    private String contactInformation;
    private String schoolCode;
    private String personInCharge;

    private boolean inSchoolRecording;
    private boolean outsideRecording;
    private boolean agencyCollaboration;
    private boolean greenScreen;
    private boolean verified;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getYoutubeChannelLink() {
        return youtubeChannelLink;
    }

    public void setYoutubeChannelLink(String youtubeChannelLink) {
        this.youtubeChannelLink = youtubeChannelLink;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
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

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
