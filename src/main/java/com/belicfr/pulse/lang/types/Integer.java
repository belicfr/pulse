/*
 * pulse - Integer
 * 02/09/2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.lang.types;

import com.belicfr.pulse.exceptions.PulseCannotStoreAsGivenTypeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Integer extends Type {
    private static final String REGEX_NUMERIC
        = "^([0-9]+)$";

    private int value;

    public Integer(String expression)
    throws PulseCannotStoreAsGivenTypeException {
        super(expression);

        this.store();
    }

    /**
     * Attempts to store given expression as an integer
     * into Pulse heap.
     *
     * @throws PulseCannotStoreAsGivenTypeException If expression is not
     *                                              numeric
     */
    private void store()
    throws PulseCannotStoreAsGivenTypeException {
        if (!isCompatible(this.getExpression())) {
            throw new PulseCannotStoreAsGivenTypeException(
                this.getExpression(),
                getClass());
        }

        this.value = java.lang.Integer.getInteger(super.getExpression());
    }

    /**
     * @return Current value
     */
    public int getValue() {
        return this.value;
    }

    public static boolean isCompatible(String expression) {
        Pattern numericPattern;
        Matcher numericMatcher;

        numericPattern = Pattern.compile(REGEX_NUMERIC);
        numericMatcher = numericPattern.matcher(expression);

        return numericMatcher.matches();
    }
}
