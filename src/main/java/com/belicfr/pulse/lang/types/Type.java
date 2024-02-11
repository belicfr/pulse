/*
 * pulse - Type
 * 02/09/2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.lang.types;

import com.belicfr.pulse.file.PulseInstructionLine;

public class Type {
    public static final int MINIMUM_LINE_PARTS_COUNT = 3;

    private String expression;

    public Type(String expression) {
        this.expression = expression;
    }

    /**
     * @return Expression associated to current object
     */
    public String getExpression() {
        return this.expression;
    }

    /**
     * Appends given line to expression.
     *
     * @param line Pil to append
     */
    protected void addLineToExpression(PulseInstructionLine line) {
        this.expression += String.format("\n%s", line.getContent());
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
