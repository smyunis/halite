package com.smyunis.halite.domain.cateringeventhost;

public class CateringEventHost {
    private final CateringEventHostData data;

    public CateringEventHost(CateringEventHostData data) {
        this.data = data;
    }

    public CateringEventHostDataReadOnlyProxy getDataReadOnlyProxy() {
        return new CateringEventHostDataReadOnlyProxy(data);
    }

}
