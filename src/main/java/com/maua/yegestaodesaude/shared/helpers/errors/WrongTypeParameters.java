package com.maua.yegestaodesaude.shared.helpers.errors;

public class WrongTypeParameters extends RuntimeException {
    public WrongTypeParameters(String fieldName, String fieldTypeExpected, String fieldTypeReceived) {
        super("Field " + fieldName + " isn't in the right type.\n Received: " + fieldTypeReceived + ".\n Expected: "
                + fieldTypeExpected + ".");
    }
}
