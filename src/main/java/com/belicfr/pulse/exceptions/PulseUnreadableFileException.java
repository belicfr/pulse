/*
 * pulse - PulseFileNotFoundException
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.exceptions;

public class PulseUnreadableFileException extends Exception {
    private static final String EXCEPTION_MESSAGE_PATTERN
        = "%s Pulse file cannot be read.";

    private String message;

    public PulseUnreadableFileException(String path) {
        this.message = String.format(EXCEPTION_MESSAGE_PATTERN, path);
    }

    public String getMessage() {
        return message;
    }
}
