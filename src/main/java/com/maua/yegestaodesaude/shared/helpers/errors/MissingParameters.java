package com.maua.yegestaodesaude.shared.helpers.errors;

public class MissingParameters extends RuntimeException {
    public MissingParameters(String message) {
        super("Field " + message + " is missing");
    }
}
