/*
 * pulse - FunctionType
 * 02-10-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.lang.types;

import com.belicfr.pulse.exceptions.PulseAttemptToGetFunctionValueException;
import com.belicfr.pulse.exceptions.PulseCannotStoreAsGivenTypeException;
import com.belicfr.pulse.file.PulseInstructionLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionType extends Type implements TypeInterface {
    public static final int MINIMUM_LINE_PARTS_COUNT = 2;

    private static final String REGEX_FUNCTION
        = "(?i)((->)(\\s*)(([a-z])([a-z0-9]*)(\\s*)"
        + ",(\\s*)*)(([a-z])([a-z0-9]*))?)?";

    private List<PulseInstructionLine> body;

    public FunctionType(String expression)
    throws PulseCannotStoreAsGivenTypeException {
        super(expression);

        this.body = new ArrayList<>();

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

    /**
     * Attempts to run object function.
     */
    public void run() {
        // TODO: run method by getting function body!
    }

    /**
     * @return Function body
     */
    public List<PulseInstructionLine> getBody() {
        return this.body;
    }

    @Override
    public Object getValue()
    throws PulseAttemptToGetFunctionValueException {
        throw new PulseAttemptToGetFunctionValueException("{undefined}");
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
