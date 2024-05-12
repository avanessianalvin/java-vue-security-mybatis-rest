package com.sian.javaprojecttemplates.restvue.util;

import net.sf.oval.ConstraintViolation;

import java.util.List;
import java.util.StringJoiner;

public class Validator {

    private static final net.sf.oval.Validator validator = new net.sf.oval.Validator();

    public static String getErrorsMessage(Object o) {

        List<ConstraintViolation> violationList = validator.validate(o);

        StringJoiner joiner = new StringJoiner("\n");
        violationList.forEach(constraintViolation -> joiner.add(extractModifiedErrorMessage(constraintViolation.getMessage())));
        return joiner.toString();
    }

    private static String extractModifiedErrorMessage(String errorMessage) {
        // Find the index of the last dot
        int lastDotIndex = errorMessage.lastIndexOf('.');

        // Find the index of the second-to-last dot
        int secondLastDotIndex = errorMessage.lastIndexOf('.', lastDotIndex - 1);

        // Extract the substring after the second-to-last dot
        String modifiedMessage = errorMessage.substring(secondLastDotIndex + 1);

        // Replace the first dot with a space
        modifiedMessage = modifiedMessage.replaceFirst("\\.", " ");

        return modifiedMessage;
    }

}
