package com.smyunis.halite.domain.cateringeventhost;

public class CateringEventHost {
    private CateringEventHostId id = new CateringEventHostId();
    private String name;
    private PhoneNumber phoneNumber;

    public CateringEventHostId getId() {
        return id;
    }

    void setId(CateringEventHostId id) {
        this.id = id;
    }


    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }


}
