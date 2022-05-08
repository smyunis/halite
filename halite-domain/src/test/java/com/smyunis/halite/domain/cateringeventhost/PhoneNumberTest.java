package com.smyunis.halite.domain.cateringeventhost;

import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhoneNumberTest {

    @ParameterizedTest
    @ValueSource(strings = {"0912096265","+251929186232"})
    void acceptsValidPhoneNumber(String phoneNo) {
        PhoneNumber phoneNumber = new PhoneNumber(phoneNo);

        assertEquals(phoneNo, phoneNumber.phoneNumber());
    }

    @Test
    void throwsExceptionForInvalidPhoneNumber() {
        String invalidPhoneNo = "INV-Err_091209...";

        assertThrows(InvalidValueException.class,() -> {
            PhoneNumber phoneNumber = new PhoneNumber(invalidPhoneNo);
        });
    }

}
