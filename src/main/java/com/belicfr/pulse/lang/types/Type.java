/*
 * pulse - Type
 * 02/09/2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.lang.types;

import com.belicfr.pulse.exceptions.PulseCannotStoreAsGivenTypeException;

public class Type {
    private String expression;

    public Type(String expression)
    throws PulseCannotStoreAsGivenTypeException {
        this.expression = expression;
    }

    /**
     * @return Expression associated to current object
     */
    public String getExpression() {
        return this.expression;
    }

    /**
     * @param expression Given expression
     * @return If given expression can be stored as class
     *         type
     */
    public static boolean isCompatible(String expression) {
        return true;
    }
}
