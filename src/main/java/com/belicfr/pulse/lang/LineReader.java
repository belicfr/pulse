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

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineReader {
    private static final String REGEX_VARIABLE_DEFINITION_INSTRUCTION
        = "(?i)(([a-z])([a-z0-9]*))((\\s*)=(\\s*)(.+))";

    private static final String REGEX_FUNCTION_DEFINITION_INSTRUCTION
        = "function(\\s+)(([a-z])([a-zA-Z0-9]*))(\\s*)(.*)";

    private static final String REGEX_CALL_INSTRUCTION
        = "(?i):(([a-z])([a-z0-9]*))";

    private static final String REGEX_PRINT_INSTRUCTION
        = "print(\\s+)(.+)";

    private static String lastBlockRegisteredKey;

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
           PulseAttemptToGetFunctionValueException,
           PulseIndentLevelLowException,
           PulseBadIndentException,
           PulseUndefinedEntityException,
           PulseUnrunnableEntityException {

        int lineIndentLevel;

        String lineContent;

        Pattern variableDefinitionPattern,
                functionDefinitionPattern,
                callPattern,
                printPattern;

        Matcher variableDefinitionMatcher,
                functionDefinitionMatcher,
                callMatcher,
                printMatcher;

        lineIndentLevel = this.getLine().getIndentLevel();

        if (lineIndentLevel > 0) {
            if (!hasLastBlock()) {
                throw new PulseBadIndentException(this.getLine()
                                                      .getContent());
            }

            getLastBlock().addToBody(this.getLine());

            if (getLastBlock() instanceof FunctionType) {
                this.getFileHeap()
                    .getRegister()
                    .put(((FunctionType) getLastBlock()).getName(),
                         (FunctionType) getLastBlock());

                return;
            }
        }

        lineContent = this.getLine().getContent();

        variableDefinitionPattern = Pattern.compile(
            REGEX_VARIABLE_DEFINITION_INSTRUCTION);

        functionDefinitionPattern = Pattern.compile(
            REGEX_FUNCTION_DEFINITION_INSTRUCTION);

        callPattern = Pattern.compile(REGEX_CALL_INSTRUCTION);

        printPattern = Pattern.compile(
            REGEX_PRINT_INSTRUCTION);

        variableDefinitionMatcher
            = variableDefinitionPattern.matcher(lineContent);

        functionDefinitionMatcher
            = functionDefinitionPattern.matcher(lineContent);

        callMatcher = callPattern.matcher(lineContent);

        printMatcher = printPattern.matcher(lineContent);

        if (variableDefinitionMatcher.matches()) {
            this.defineVariable(variableDefinitionMatcher);
        } else if (functionDefinitionMatcher.matches()) {
            this.defineFunction(functionDefinitionMatcher);
        } else if (callMatcher.matches()) {
            this.call(callMatcher);
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

        value = this.getPulseValue(variableValue);

        this.getFileHeap()
            .add(this.getLine()
                     .getSplitParts()
                     .get(0),
                 value);

    }

    private void defineFunction(Matcher functionDefinitionMatcher)
    throws PulseInvalidInstructionException,
           PulseCannotStoreAsGivenTypeException {

        String functionName;

        FunctionType function;

        List<String> lineParts;

        functionName = functionDefinitionMatcher.group(2);

        lineParts = this.getLine()
                        .getSplitParts();

        if (lineParts.size() < FunctionType.MINIMUM_LINE_PARTS_COUNT) {
            throw new PulseInvalidInstructionException(this.getLine()
                                                           .getContent());
        }

        function = new FunctionType(functionName,
                                    functionDefinitionMatcher.group(6));

        this.getFileHeap()
            .add(functionName,
                 function);

        setLastBlockRegisteredKey(functionName);

    }

    private void call(Matcher callMatcher)
    throws PulseUndefinedEntityException,
           PulseBadIndentException,
           PulseInvalidValueTypeException,
           PulseInvalidIndentLevelException,
           PulseCannotStoreAsGivenTypeException,
           PulseAttemptToGetFunctionValueException,
           PulseIndentLevelLowException,
           PulseInvalidInstructionException,
           PulseUnrunnableEntityException {

        String functionName;

        FunctionType function;

        HashMap<String, TypeInterface> register;

        functionName = callMatcher.group(1);

        register = this.getFileHeap()
                       .getRegister();


        if (!register.containsKey(functionName)) {
            throw new PulseUndefinedEntityException(functionName);
        }

        if (!(register.get(functionName) instanceof FunctionType)) {
            throw new PulseUnrunnableEntityException(functionName);
        }

        function = (FunctionType) register.get(functionName);
        function.run();

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

        value = this.getPulseValue(this.getLine()
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

    private TypeInterface getPulseValue(String definitionValue)
    throws PulseCannotStoreAsGivenTypeException,
           PulseInvalidValueTypeException {

        HashMap<String, TypeInterface> heapRegister;

        heapRegister = this.getFileHeap().getRegister();

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

    public BlockTypeInterface getLastBlock() {
        return (BlockTypeInterface) this.getFileHeap()
                                        .getRegister()
                                        .get(getLastBlockRegisteredKey());
    }

    /**
     * @return If there is a registered block instruction
     */
    public static boolean hasLastBlock() {
        return lastBlockRegisteredKey != null;
    }

    /**
     * @return Last registered block type key
     */
    public static String getLastBlockRegisteredKey() {
        return lastBlockRegisteredKey;
    }

    /**
     * @param lastBlockRegisterKey New registered block type key
     */
    public static void setLastBlockRegisteredKey(String lastBlockRegisterKey) {
        LineReader.lastBlockRegisteredKey = lastBlockRegisterKey;
    }
}
