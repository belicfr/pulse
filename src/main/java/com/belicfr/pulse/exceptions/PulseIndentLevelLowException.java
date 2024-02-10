/*
 * pulse - PulseFileNotFoundException
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.exceptions;

public class PulseIndentLevelLowException extends Exception {
    private static final String EXCEPTION_MESSAGE_PATTERN
        = "Attempt to remove a number of indentations exceeding indentation "
        + "level of following line: {%s}";

    private String message;

    public PulseIndentLevelLowException(String line) {

        this.message = String.format(EXCEPTION_MESSAGE_PATTERN,
                                     line);

    }

    public String getMessage() {
        return message;
    }
}
