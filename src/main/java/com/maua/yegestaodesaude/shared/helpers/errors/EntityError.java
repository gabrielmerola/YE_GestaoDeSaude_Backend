package com.maua.yegestaodesaude.shared.helpers.errors;

public class EntityError extends RuntimeException {
    public EntityError(String message) {
        super("Field " + message + " is not valid");
    }

}
