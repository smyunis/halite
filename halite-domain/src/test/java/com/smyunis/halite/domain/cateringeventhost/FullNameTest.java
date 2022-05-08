package com.smyunis.halite.domain.cateringeventhost;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FullNameTest {
    @Test
    void canGetFirstName() {
        String firstName = "James";
        FullName fullname = new FullName(firstName, null);

        var result = fullname.getFirstName();

        assertEquals(firstName, result);
    }

    @Test
    void hasLastName() {
        String lastName = "Bond";
        FullName fullName = new FullName(null, lastName);

        var result = fullName.getLastName();

        assertEquals(lastName, result);
    }

    @Test
    void canGetFullNameStringFromFirstnameAndLastname() {
        String fullNameString = "James Bond";
        String[] splitFullName = fullNameString.split(" ");
        FullName fullName = new FullName(splitFullName[0], splitFullName[1]);

        String result = fullName.getFullName();

        assertEquals(fullNameString, result);
    }

    @Test
    void canStoreFullNameFromAFullNameStringWithOnlyFirstName() {
        String fullNameWithOnlyFirstName = "James";

        assertFullNameIsReturned(fullNameWithOnlyFirstName);
    }

    @Test
    void canStoreFullNameFromAFullNameStringWithFirstAndLastName() {
        String fullNameString = "James Bond";

        assertFullNameIsReturned(fullNameString);
    }

    @Test
    void lastNameIsEmptyStringIfOnlyFirstNameInFullName() {
        String fullNameString = "James";
        FullName fullName = new FullName(fullNameString);

        String result = fullName.getLastName();

        assertEquals("",result);
    }

    @Test
    void canGetFirstAndLastNameFromFullName() {
        String fullNameString = "James Bond";
        FullName fullName = new FullName(fullNameString);

        assertEquals("James",fullName.getFirstName());
        assertEquals("Bond",fullName.getLastName());
    }

    @Test
    void canSetNameWithMiddleName() {
        String fullNameString = "James Herbert Bond";
        FullName fullName = new FullName(fullNameString);

        assertEquals("James",fullName.getFirstName());
        assertEquals("Herbert Bond",fullName.getLastName());
        assertEquals(fullNameString,fullName.getFullName());
    }

    private void assertFullNameIsReturned(String fullNameWithOnlyFirstName) {
        FullName fullName = new FullName(fullNameWithOnlyFirstName);
        assertEquals(fullNameWithOnlyFirstName, fullName.getFullName());
    }

}
