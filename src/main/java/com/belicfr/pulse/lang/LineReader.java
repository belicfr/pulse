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

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineReader {
    private static final String REGEX_VARIABLE_DEFINITION
        = "(?i)([a-z])([a-z0-9]*)((\\s*)=(\\s*)(.+))";

    private Heap fileHeap;

    private PulseInstructionLine line;

    public LineReader(Heap fileHeap, PulseInstructionLine line) {
        this.fileHeap = fileHeap;
        this.line     = line;
    }

    public void read()
    throws PulseCannotStoreAsGivenTypeException,
           PulseInvalidInstructionException, PulseInvalidValueTypeException {

        Pattern variableDefinitionPattern;

        Matcher readMatcher;

        variableDefinitionPattern = Pattern.compile(REGEX_VARIABLE_DEFINITION);

        readMatcher = variableDefinitionPattern.matcher(this.getLine()
                                                            .getContent());

        if (readMatcher.matches()) {
            this.defineVariable();
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

        String definitionValue;

        lineParts = this.getLine()
                        .getSplitParts();

        if (lineParts.size() != 3) {
            throw new PulseInvalidInstructionException(this.getLine()
                                                           .getContent());
        }

        definitionValue = lineParts.get(2);

        if (IntegerType.isCompatible(definitionValue)) {
            value = new IntegerType(definitionValue);
        } else if (StringType.isCompatible(definitionValue)) {
            value = new StringType(definitionValue);
        } else if (BooleanType.isCompatible(definitionValue)) {
            value = new BooleanType(definitionValue);
        } else {
            throw new PulseInvalidValueTypeException(definitionValue);
        }

        this.getFileHeap()
            .add(this.getLine()
                     .getSplitParts()
                     .get(0),
                 value);

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
}
