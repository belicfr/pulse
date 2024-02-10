/*
 * pulse - TypeInterface
 * 02-09-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.lang.types;

import com.belicfr.pulse.exceptions.PulseAttemptToGetFunctionValueException;

public interface TypeInterface {
    String getExpression();

    Object getValue()
    throws PulseAttemptToGetFunctionValueException;

    static boolean isCompatible() {
        return false;
    }
}
