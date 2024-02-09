/*
 * pulse - PulseFileNotFoundException
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.exceptions;

public class PulseCannotStoreAsGivenTypeException extends Exception {
    private static final String EXCEPTION_MESSAGE_PATTERN
        = "%s cannot be stored as %s.";

    private String message;

    public PulseCannotStoreAsGivenTypeException(String expression,
                                                Class type) {

        this.message = String.format(EXCEPTION_MESSAGE_PATTERN,
                                     expression,
                                     type.getName());

    }

    public String getMessage() {
        return message;
    }
}
