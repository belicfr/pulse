/*
 * pulse - PulseFileNotFoundException
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.exceptions;

public class PulseInvalidInstructionException extends Exception {
    private static final String EXCEPTION_MESSAGE_PATTERN
        = "%s Pulse instruction is invalid.";

    private String message;

    public PulseInvalidInstructionException(String expression) {

        this.message = String.format(EXCEPTION_MESSAGE_PATTERN,
                                     expression);

    }

    public String getMessage() {
        return message;
    }
}
