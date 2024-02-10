/*
 * pulse - PulseFileNotFoundException
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.exceptions;

public class PulseAttemptToGetFunctionValueException extends Exception {
    private static final String EXCEPTION_MESSAGE_PATTERN
        = "Attempt to access the value of %s function.";

    private String message;

    public PulseAttemptToGetFunctionValueException(String functionName) {

        this.message = String.format(EXCEPTION_MESSAGE_PATTERN,
                                     functionName);

    }

    public String getMessage() {
        return message;
    }
}
