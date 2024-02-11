/*
 * pulse - PulseFileNotFoundException
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.exceptions;

public class PulseBadIndentException extends Exception {
    private static final String EXCEPTION_MESSAGE_PATTERN
        = "Following line is badly indented: {%s}";

    private String message;

    public PulseBadIndentException(String line) {

        this.message = String.format(EXCEPTION_MESSAGE_PATTERN,
                                     line);

    }

    public String getMessage() {
        return message;
    }
}
