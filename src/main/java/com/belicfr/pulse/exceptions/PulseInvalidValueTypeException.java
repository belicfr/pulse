/*
 * pulse - PulseFileNotFoundException
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.exceptions;

public class PulseInvalidValueTypeException extends Exception {
    private static final String EXCEPTION_MESSAGE_PATTERN
        = "%s type cannot be found by Pulse interpreter.";

    private String message;

    public PulseInvalidValueTypeException(String value) {

        this.message = String.format(EXCEPTION_MESSAGE_PATTERN,
                                     value);

    }

    public String getMessage() {
        return message;
    }
}
