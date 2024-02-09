/*
 * pulse - LineReader
 * 02/09/2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.lang;

import com.belicfr.pulse.file.PulseInstructionLine;
import com.belicfr.pulse.heap.Heap;
import com.belicfr.pulse.lang.types.Integer;
import com.belicfr.pulse.lang.types.Type;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineReader {
    private static final String REGEX_VARIABLE_DEFINITION
        = "([a-z])([a-z0-9]*)(\s*=\s*)(.+)";

    private Heap fileHeap;

    private PulseInstructionLine line;

    public LineReader(Heap fileHeap, PulseInstructionLine line) {
        this.fileHeap = fileHeap;
        this.line     = line;
    }

    public void read() {
        // Variable definition?

        // TODO...
    }

    private void variableDefinition() {
        List<String> lineParts;



        Pattern variableDefinitionPattern;
        Matcher variableDefinitionMatcher;

        lineParts = this.getLine().getSplitParts();

        variableDefinitionPattern = Pattern.compile(REGEX_VARIABLE_DEFINITION);
        variableDefinitionMatcher
            = variableDefinitionPattern.matcher(this.getLine().toString());

        if (variableDefinitionMatcher.matches()) {
            this.getFileHeap().add(lineParts.get(0),
                                   );
        }
    }

    private Class guessType() {
        String lineContent;
        lineContent = this.line.getContent();

        if (Integer.isCompatible(lineContent)) {
            return Integer.class;
        }
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
