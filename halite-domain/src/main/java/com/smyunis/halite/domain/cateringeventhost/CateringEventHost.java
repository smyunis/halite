package com.smyunis.halite.domain.cateringeventhost;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringevent.CateringEventId;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CateringEventHost {
    private CateringEventHostId id = new CateringEventHostId();
    private String name;
    private PhoneNumber phoneNumber;
    private Set<CateringEventId> hostedCateringEventsIds = new HashSet<>();
    private final List<DomainEvent> domainEvents = new ArrayList<>();

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

    public Set<CateringEventId> getHostedCateringEvents() {
        return hostedCateringEventsIds;
    }

    public void hostNewCateringEvent(CateringEventId cateringEventId) {
        this.hostedCateringEventsIds.add(cateringEventId);
    }

    public void removeCateringEvent(CateringEventId cateringEventId) {
        this.hostedCateringEventsIds.remove(cateringEventId);
    }

    public List<DomainEvent> getDomainEvents() {
        return domainEvents;
    }


}
