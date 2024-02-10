/*
 * pulse - LineReader
 * 02/09/2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.lang;

import com.belicfr.pulse.exceptions.PulseCannotStoreAsGivenTypeException;
import com.belicfr.pulse.exceptions.PulseInvalidInstructionException;
import com.belicfr.pulse.exceptions.PulseInvalidValueTypeException;
import com.belicfr.pulse.file.PulseInstructionLine;
import com.belicfr.pulse.heap.Heap;
import com.belicfr.pulse.lang.types.BooleanType;
import com.belicfr.pulse.lang.types.IntegerType;
import com.belicfr.pulse.lang.types.StringType;
import com.belicfr.pulse.lang.types.TypeInterface;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineReader {
    private static final String REGEX_VARIABLE_DEFINITION_INSTRUCTION
        = "(?i)([a-z])([a-z0-9]*)((\\s*)=(\\s*)(.+))";

    private static final String REGEX_PRINT_INSTRUCTION
        = "print(\\s*)(.+)";

    private Heap fileHeap;

    private PulseInstructionLine line;

    public LineReader(Heap fileHeap, PulseInstructionLine line) {
        this.fileHeap = fileHeap;
        this.line     = line;
    }

    public void read()
    throws PulseCannotStoreAsGivenTypeException,
           PulseInvalidInstructionException, PulseInvalidValueTypeException {

        String lineContent;

        Pattern variableDefinitionPattern,
                printPattern;

        Matcher variableDefinitionMatcher,
                printMatcher;

        lineContent = this.getLine().getContent();

        variableDefinitionPattern = Pattern.compile(
            REGEX_VARIABLE_DEFINITION_INSTRUCTION);

        printPattern = Pattern.compile(
            REGEX_PRINT_INSTRUCTION);

        variableDefinitionMatcher
            = variableDefinitionPattern.matcher(lineContent);

        printMatcher = printPattern.matcher(lineContent);

        if (variableDefinitionMatcher.matches()) {
            this.defineVariable();
        } else if (printMatcher.matches()) {
            this.print();
        } else {
            throw new PulseInvalidInstructionException(
                this.getLine().getContent());
        }

    }

    private void defineVariable()
    throws PulseCannotStoreAsGivenTypeException,
           PulseInvalidInstructionException,
           PulseInvalidValueTypeException {

        TypeInterface value;

        List<String> lineParts;

        lineParts = this.getLine()
                        .getSplitParts();

        if (lineParts.size() < 3) {
            throw new PulseInvalidInstructionException(this.getLine()
                                                           .getContent());
        }

        value = getPulseValue(this.getFileHeap(), this.getLine()
                                                      .getSplitParts()
                                                      .get(2));

        this.getFileHeap()
            .add(this.getLine()
                     .getSplitParts()
                     .get(0),
                 value);

    }

    private void print()
    throws PulseInvalidInstructionException,
           PulseInvalidValueTypeException,
           PulseCannotStoreAsGivenTypeException {

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
