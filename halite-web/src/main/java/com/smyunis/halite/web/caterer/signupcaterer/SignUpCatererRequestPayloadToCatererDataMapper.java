package com.smyunis.halite.web.caterer.signupcaterer;

import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import com.smyunis.halite.domain.shared.PhoneNumber;
import com.smyunis.halite.web.Mapper;

import java.net.MalformedURLException;
import java.net.URL;

public class SignUpCatererRequestPayloadToCatererDataMapper implements Mapper<SignUpCatererRequestPayload, CatererData> {
    @Override
    public CatererData map(SignUpCatererRequestPayload obj) {
        try {
            return tryMapCatererData(obj);
        } catch (MalformedURLException exception) {
            throw new InvalidValueException(exception.getMessage(), exception);
        }
    }

    private CatererData tryMapCatererData(SignUpCatererRequestPayload obj) throws MalformedURLException {
        return new CatererData()
                .setPhoneNumber(new PhoneNumber(obj.getPhoneNumber()))
                .setName(obj.getName())
                .setPersonalDescription(obj.getPersonalDescription())
                .setCatererImage(new URL(obj.getProfileImage()));
    }
}
