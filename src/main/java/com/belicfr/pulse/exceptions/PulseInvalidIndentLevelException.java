/*
 * pulse - PulseFileNotFoundException
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.exceptions;

public class PulseInvalidIndentLevelException extends Exception {
    private static final String EXCEPTION_MESSAGE_PATTERN
        = "Invalid indent at line:\n%s";

    private String message;

    public PulseInvalidIndentLevelException(String line) {
        this.message = String.format(EXCEPTION_MESSAGE_PATTERN, line);
    }

    public String getMessage() {
        return message;
    }
}
