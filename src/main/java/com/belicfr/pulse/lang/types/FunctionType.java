/*
 * pulse - FunctionType
 * 02-10-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.lang.types;

import com.belicfr.pulse.exceptions.PulseCannotStoreAsGivenTypeException;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionType extends Type implements TypeInterface {
    private static final String REGEX_FUNCTION
        = "(?:->\\s*(.*)(?:\\R.*|$))|(\\R.*)";

    private boolean value;

    public FunctionType(String expression)
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
    }

    public void run() {
        String[] functionBodyLines;
        functionBodyLines = this.getExpression()
                                .split("\n");

        System.out.println(Arrays.toString(functionBodyLines));
    }

    /**
     * @param expression Given expression
     * @return If given expression is a function
     */
    public static boolean isCompatible(String expression) {
        Pattern functionPattern;
        Matcher functionMatcher;

        functionPattern = Pattern.compile(REGEX_FUNCTION);
        functionMatcher = functionPattern.matcher(expression);

        return functionMatcher.matches();
    }
}
