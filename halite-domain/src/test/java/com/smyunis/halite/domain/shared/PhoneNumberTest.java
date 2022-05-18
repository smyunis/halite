package com.smyunis.halite.domain.shared;

import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhoneNumberTest {

    @Test
    void acceptsValidPhoneNumber() {
        String phoneNo = "+251929186232";
        PhoneNumber phoneNumber = new PhoneNumber(phoneNo);

        assertEquals(phoneNo, phoneNumber.phoneNumber());
    }

    @Test
    void throwsExceptionForInvalidPhoneNumber() {
        String invalidPhoneNo = "INV-Err_091209...";

        assertThrows(InvalidValueException.class, () -> {
            PhoneNumber phoneNumber = new PhoneNumber(invalidPhoneNo);
        });
    }

}
