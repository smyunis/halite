package com.smyunis.halite.domain.shared;

import com.smyunis.halite.domain.domainexceptions.InvalidValueException;

import java.util.regex.Pattern;

public record PhoneNumber(String phoneNumber) {
    public PhoneNumber {
        boolean isValidPhoneNumber = Pattern.compile("(\\+251|0)[0-9]{9}")
                .matcher(phoneNumber)
                .matches();

        if(!isValidPhoneNumber)
            throw new InvalidValueException(this.getClass().getName());
    }
}
