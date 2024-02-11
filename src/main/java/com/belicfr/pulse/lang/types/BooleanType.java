/*
 * pulse - BooleanType
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

public class BooleanType extends Type implements TypeInterface {
    private static final String REGEX_NUMERIC
        = "^(true|false)$";

    private boolean value;

    public BooleanType(String expression)
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
                this.getExpression(), getClass());
        }

        this.value = Boolean.parseBoolean(super.getExpression());
    }

    public String getExpression() {
        return super.getExpression().trim();
    }

    /**
     * @return Current value
     */
    @Override
    public Boolean getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }

    /**
     * @param expression Given expression
     * @return If given expression is numeric
     */
    public static boolean isCompatible(String expression) {
        Pattern numericPattern;
        Matcher numericMatcher;

        numericPattern = Pattern.compile(REGEX_NUMERIC);
        numericMatcher = numericPattern.matcher(expression);

        return numericMatcher.matches();
    }
}
