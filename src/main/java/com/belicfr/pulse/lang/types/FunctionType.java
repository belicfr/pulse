/*
 * pulse - FunctionType
 * 02-10-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.lang.types;

import com.belicfr.pulse.exceptions.*;
import com.belicfr.pulse.file.PulseInstructionLine;
import com.belicfr.pulse.heap.Heap;
import com.belicfr.pulse.lang.LineReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionType
extends Type
implements TypeInterface, BlockTypeInterface {
    public static final int MINIMUM_LINE_PARTS_COUNT = 2;

    private static final String REGEX_FUNCTION
        = "(?i)((->)(\\s*)(([a-z])([a-z0-9]*)(\\s*),"
        + "(\\s*))?(([a-z])([a-z0-9]*)))?";

    private final String name;

    private final List<PulseInstructionLine> body;

    public FunctionType(String name, String expression)
    throws PulseCannotStoreAsGivenTypeException {
        super(expression);

        this.name = name;
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
        if (!isCompatible(super.getExpression().trim())) {
            throw new PulseCannotStoreAsGivenTypeException(
                this.getExpression(), getClass());
        }
    }

    /**
     * Attempts to run object function.
     */
    public void run()
    throws PulseBadIndentException,
           PulseInvalidValueTypeException,
           PulseInvalidIndentLevelException,
           PulseCannotStoreAsGivenTypeException,
           PulseAttemptToGetFunctionValueException,
           PulseIndentLevelLowException,
           PulseInvalidInstructionException,
           PulseUndefinedEntityException,
           PulseUnrunnableEntityException {

        Heap heap;
        heap = new Heap();

        for (PulseInstructionLine line: this.getBody()) {
            line.removeIndent();

            (new LineReader(heap, line))
                .read();
        }

    }

    /**
     * @return Function body
     */
    public List<PulseInstructionLine> getBody() {
        return this.body;
    }

    /**
     * Adds Pil to current function body.
     *
     * @param line Pil to add
     */
    public void addToBody(PulseInstructionLine line) {
        this.body.add(line);
        this.addLineToExpression(line);
    }

    /**
     * @return Function name
     */
    public String getName() {
        return this.name;
    }

    public String getExpression() {
        StringBuilder expression;
        expression = new StringBuilder();

        for (String line: super.getExpression().split("\n")) {
            if (!line.isBlank()) {
                expression.append(line)
                          .append('\n');
            }
        }

        return expression.toString();
    }

    @Override
    public Object getValue()
    throws PulseAttemptToGetFunctionValueException {
        throw new PulseAttemptToGetFunctionValueException(this.getName());
    }

    @Override
    public String toString() {
        return '\n' + this.getExpression() + '\n';
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
