package com.smyunis.halite.domain.shared;

import com.smyunis.halite.domain.domainexceptions.InvalidValueException;

import java.util.regex.Pattern;

public record PhoneNumber(String phoneNumber) {
    public PhoneNumber {
        String internationalPhoneNumberRegexPattern = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        //String ethiopianPhoneNumberRegexPattern = "(\\+251|0)[0-9]{9}";

        boolean isValidPhoneNumber = Pattern.compile(internationalPhoneNumberRegexPattern)
                .matcher(phoneNumber)
                .matches();

        if(!isValidPhoneNumber)
            throw new InvalidValueException(this.getClass().getName());
    }
}
