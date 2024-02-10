/*
 * pulse - LineReader
 * 02/09/2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.lang;

import com.belicfr.pulse.exceptions.*;
import com.belicfr.pulse.file.PulseInstructionLine;
import com.belicfr.pulse.heap.Heap;
import com.belicfr.pulse.lang.types.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineReader {
    private static final String REGEX_VARIABLE_DEFINITION_INSTRUCTION
        = "(?i)(([a-z])([a-z0-9]*))((\\s*)=(\\s*)(.+))";

    private static final String REGEX_FUNCTION_DEFINITION_INSTRUCTION
        = "function(\\s+)(([a-z])([a-zA-Z0-9]*))(.*)";

    private static final String REGEX_PRINT_INSTRUCTION
        = "print(\\s+)(.+)";

    private Heap fileHeap;

    private PulseInstructionLine line;

    public LineReader(Heap fileHeap, PulseInstructionLine line) {
        this.fileHeap = fileHeap;
        this.line = line;
    }

    public void read()
    throws PulseCannotStoreAsGivenTypeException,
           PulseInvalidInstructionException,
           PulseInvalidValueTypeException,
           PulseInvalidIndentLevelException,
           PulseAttemptToGetFunctionValueException {

        int lineIndentLevel;

        String lineContent;

        Pattern variableDefinitionPattern,
                functionDefinitionPattern,
                printPattern;

        Matcher variableDefinitionMatcher,
                functionDefinitionMatcher,
                printMatcher;

        lineIndentLevel = this.getLine().getIndentLevel();

        if (lineIndentLevel > 0) {
            return;
        }

        lineContent = this.getLine().getContent();

        variableDefinitionPattern = Pattern.compile(
            REGEX_VARIABLE_DEFINITION_INSTRUCTION);

        functionDefinitionPattern = Pattern.compile(
            REGEX_FUNCTION_DEFINITION_INSTRUCTION);

        printPattern = Pattern.compile(
            REGEX_PRINT_INSTRUCTION);

        variableDefinitionMatcher
            = variableDefinitionPattern.matcher(lineContent);

        functionDefinitionMatcher
            = functionDefinitionPattern.matcher(lineContent);

        printMatcher = printPattern.matcher(lineContent);

        if (variableDefinitionMatcher.matches()) {
            this.defineVariable(variableDefinitionMatcher);
        } else if (functionDefinitionMatcher.matches()) {
            this.defineFunction(functionDefinitionMatcher);
        } else if (printMatcher.matches()) {
            this.print();
        } else {
            throw new PulseInvalidInstructionException(
                this.getLine().getContent());
        }

    }

    private void defineVariable(Matcher variableDefinitionMatcher)
    throws PulseCannotStoreAsGivenTypeException,
           PulseInvalidInstructionException,
           PulseInvalidValueTypeException {

        String variableValue;

        TypeInterface value;

        List<String> lineParts;

        lineParts = this.getLine()
                        .getSplitParts();

        variableValue = variableDefinitionMatcher.group(7);

        if (lineParts.size() < Type.MINIMUM_LINE_PARTS_COUNT) {
            throw new PulseInvalidInstructionException(this.getLine()
                                                           .getContent());
        }

        value = getPulseValue(this.getFileHeap(), variableValue);

        this.getFileHeap()
            .add(this.getLine()
                     .getSplitParts()
                     .get(0),
                 value);

    }

    private void defineFunction(Matcher functionDefinitionMatcher)
    throws PulseInvalidInstructionException,
           PulseCannotStoreAsGivenTypeException {

        FunctionType function;

        List<String> lineParts;

        lineParts = this.getLine()
                        .getSplitParts();

        if (lineParts.size() < FunctionType.MINIMUM_LINE_PARTS_COUNT) {
            throw new PulseInvalidInstructionException(this.getLine()
                                                           .getContent());
        }

        function = new FunctionType(functionDefinitionMatcher.group(5));
        System.out.println(function);

        this.getFileHeap()
            .add(functionDefinitionMatcher.group(2),
                 function);

    }

    private void print()
    throws PulseInvalidInstructionException,
           PulseInvalidValueTypeException,
           PulseCannotStoreAsGivenTypeException,
           PulseAttemptToGetFunctionValueException {

        TypeInterface value;

        if (this.getLine().getSplitParts().size() < 2) {
            throw new PulseInvalidInstructionException(this.getLine()
                                                           .getContent());
        }

        value = getPulseValue(this.getFileHeap(),
                              this.getLine()
                                  .getSplitParts()
                                  .get(1));

        System.out.println(value.getValue());

    }

    /**
     * @return File heap object
     */
    public Heap getFileHeap() {
        return this.fileHeap;
    }

    /**
     * @return Current read line
     */
    public PulseInstructionLine getLine() {
        return this.line;
    }

    private static TypeInterface getPulseValue(Heap heap,
                                               String definitionValue)
    throws PulseCannotStoreAsGivenTypeException,
           PulseInvalidValueTypeException {

        HashMap<String, TypeInterface> heapRegister;

        heapRegister = heap.getRegister();

        if (IntegerType.isCompatible(definitionValue)) {
            return new IntegerType(definitionValue);
        } else if (StringType.isCompatible(definitionValue)) {
            return new StringType(definitionValue);
        } else if (BooleanType.isCompatible(definitionValue)) {
            return new BooleanType(definitionValue);
        } else if (heapRegister.containsKey(definitionValue)) {
            return heapRegister.get(definitionValue);
        } else {
            throw new PulseInvalidValueTypeException(definitionValue);
        }

    }
}
