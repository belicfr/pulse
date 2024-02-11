/*
 * pulse - PulseFileNotFoundException
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.exceptions;

public class PulseUnrunnableEntityException extends Exception {
    private static final String EXCEPTION_MESSAGE_PATTERN
        = "%s cannot be run.";

    private String message;

    public PulseUnrunnableEntityException(String entityKey) {

        this.message = String.format(EXCEPTION_MESSAGE_PATTERN,
                                     entityKey);

    }

    public String getMessage() {
        return message;
    }
}
