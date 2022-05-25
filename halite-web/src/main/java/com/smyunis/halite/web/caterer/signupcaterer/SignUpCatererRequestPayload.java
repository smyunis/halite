package com.smyunis.halite.web.caterer.signupcaterer;

public class SignUpCatererRequestPayload {
    private String phoneNumber;
    private String password;
    private String name;
    private String profileImage;
    private String personalDescription;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SignUpCatererRequestPayload setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SignUpCatererRequestPayload setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public SignUpCatererRequestPayload setName(String name) {
        this.name = name;
        return this;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public SignUpCatererRequestPayload setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public String getPersonalDescription() {
        return personalDescription;
    }

    public SignUpCatererRequestPayload setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription;
        return this;
    }
}
