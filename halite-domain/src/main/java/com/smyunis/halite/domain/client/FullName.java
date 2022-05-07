package com.smyunis.halite.domain.client;

import java.util.Arrays;
import java.util.Optional;

public class FullName {
    private final String fullName;
    private final String firstName;
    private final String lastName;

    public FullName(String fullName) {
        this.fullName = fullName;
        this.firstName = getFirstName(fullName);
        this.lastName = getLastName(fullName).isPresent() ? getLastName(fullName).get() : "";
    }

    public FullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = mergeNamesToFullName(firstName, lastName);
    }

    public String getFullName() {
        return fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private Optional<String> getLastName(String fullName) {
        String[] splitName = getSplitName(fullName);
        if (splitName.length <= 1)
            return Optional.empty();
        String[] lastNamePortion = Arrays.copyOfRange(splitName, 1, splitName.length);
        String lastName = String.join(" ",lastNamePortion);
        return Optional.of(lastName);
    }

    private String getFirstName(String fullName) {
        String[] splitName = getSplitName(fullName);
        return splitName[0];
    }

    private String[] getSplitName(String fullName) {
        return fullName.split(" ");
    }

    private String mergeNamesToFullName(String firstName, String lastName) {
        return String.format("%s %s", firstName, lastName);
    }

}