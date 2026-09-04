package com.example.HostelManagementSystem_Backend.util;

public class AddressUtils {

    public static String formatAddress(String streetAddress, String city, String province, String postalCode) {
        StringBuilder addressBuilder = new StringBuilder();

        if (streetAddress != null && !streetAddress.isBlank()) {
            addressBuilder.append(streetAddress.trim());
        }
        if (city != null && !city.isBlank()) {
            if (addressBuilder.length() > 0) addressBuilder.append(", ");
            addressBuilder.append(city.trim());
        }
        if (province != null && !province.isBlank()) {
            if (addressBuilder.length() > 0) addressBuilder.append(", ");
            addressBuilder.append(province.trim());
        }
        if (postalCode != null && !postalCode.isBlank()) {
            if (addressBuilder.length() > 0) addressBuilder.append(", ");
            addressBuilder.append(postalCode.trim());
        }

        return addressBuilder.toString();
    }
}