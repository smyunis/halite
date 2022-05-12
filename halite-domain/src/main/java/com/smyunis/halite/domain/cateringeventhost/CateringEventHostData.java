package com.smyunis.halite.domain.cateringeventhost;

public class CateringEventHostData {
    private CateringEventHostId id = new CateringEventHostId();
    private String name;
    private PhoneNumber phoneNumber;

    public CateringEventHostId getId() {
        return id;
    }

    public CateringEventHostData setId(CateringEventHostId id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CateringEventHostData setName(String name) {
        this.name = name;
        return this;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public CateringEventHostData setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
