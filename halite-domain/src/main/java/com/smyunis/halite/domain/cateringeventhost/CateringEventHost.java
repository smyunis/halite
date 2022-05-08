package com.smyunis.halite.domain.cateringeventhost;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringevent.CateringEvent;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringeventhost.domainevents.CateringEventUpdatedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CateringEventHost {
    private CateringEventHostId id = new CateringEventHostId();
    private final List<DomainEvent> domainEvents = new ArrayList<>();
    private String name;
    private PhoneNumber phoneNumber;
    private List<CateringEventId> hostedCateringEventsIds = new ArrayList<>();

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

    public List<CateringEventId> getHostedCateringEvents() {
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

    public void updateCateringEvent(CateringEvent updatedCateringEvent) {
        domainEvents.add(new CateringEventUpdatedEvent(updatedCateringEvent));
    }
}
