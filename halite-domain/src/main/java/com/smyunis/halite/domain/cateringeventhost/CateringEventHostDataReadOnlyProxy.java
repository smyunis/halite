package com.smyunis.halite.domain.cateringeventhost;

import com.smyunis.halite.domain.shared.PhoneNumber;

public class CateringEventHostDataReadOnlyProxy {
    private final CateringEventHostData data;

    public CateringEventHostDataReadOnlyProxy(CateringEventHostData data) {
        this.data = data;
    }

    public CateringEventHostId getId() {
        return data.getId();
    }

    public String getName() {
        return data.getName();
    }

    public PhoneNumber getPhoneNumber() {
        return data.getPhoneNumber();
    }
}
