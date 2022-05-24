package com.smyunis.halite.web.caterer.getcaterer;

public class GetCatererResponsePayload {
    private String id;
    private String name;
    private String phoneNumber;
    private String personalDescription;
    private String catererImage;

    public String getId() {
        return id;
    }

    public GetCatererResponsePayload setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GetCatererResponsePayload setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public GetCatererResponsePayload setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPersonalDescription() {
        return personalDescription;
    }

    public GetCatererResponsePayload setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription;
        return this;
    }

    public String getCatererImage() {
        return catererImage;
    }

    public GetCatererResponsePayload setCatererImage(String catererImage) {
        this.catererImage = catererImage;
        return this;
    }
}
