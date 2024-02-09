/*
 * pulse - PulseInstructionLineTest
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.file;

import com.belicfr.pulse.PulseTestLocalEnv;
import com.belicfr.pulse.exceptions.PulseInvalidIndentLevelException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PulseInstructionLineTest {
    private static final String ERROR_PIL_PARTS_MUST_BE_EQUALS
        = "Pulse instruction line parts and local parts list must be equals.";

    private static final String ERROR_PIL_PARTS_MUST_BE_DIFFERENT
        = "Pulse instruction line parts and local parts list must "
        + "be different.";

    private static final String ERROR_PIL_INDENT_SIZE_IS_RIGHT
        = "Pulse instruction line indent size is good.";

    private static final String ERROR_PIL_INDENT_SIZE_IS_WRONG
        = "Pulse instruction line indent size is wrong.";

    private static final String ERROR_PIL_INDENT_LEVEL_IS_RIGHT
        = "Pulse instruction line indent level is good.";

    private static final String ERROR_PIL_INDENT_LEVEL_IS_WRONG
        = "Pulse instruction line indent level is wrong.";

    private static final String ERROR_PIL_INDENT_LEVEL_IS_INVALID
        = "Pulse instruction line indent level is invalid.";

    private static final String ERROR_PIL_INDENT_IS_VALID
        = "Pulse instruction line is valid.";

    private static final String ERROR_PIL_INDENT_IS_INVALID
        = "Pulse instruction line is invalid.";

    private static final String ERROR_PILS_CONTENT_MUST_BE_EQUALS
        = "Pulse instruction lines content must be equals.";

    private static final String ERROR_PILS_CONTENT_MUST_BE_DIFFERENT
        = "Pulse instruction lines content must be different.";

    private static final String ERROR_PIL_MUST_BE_EQUALS
        = "Pulse instruction lines must be equals.";

    private static final String ERROR_PIL_MUST_BE_DIFFERENT
            = "Pulse instruction lines must be different.";

    private static final String ERROR_OBJECTS_HAS_NOT_SAME_CLASS_NAME
            = "Objects are different class names.";

    private static final String LINE_CONTENT
        = "test = 1";

    private static final String INDENTED_LINE_CONTENT
        = "        age = 19";

    private final static String LINE_WITH_INVALID_INDENT_CONTENT
        = "   age = 23";

    private final static String LINE_CONTENT_WITH_STRING_USING_DOUBLE_QUOTES
        = "name = \"John\"";

    private final static
    List<String> LINE_WITH_STRING_PARTS_USING_DOUBLE_QUOTES
        = Arrays.asList("name", "=", "\"John\"");

    private final static String LINE_CONTENT_WITH_STRING_USING_SIMPLE_QUOTES
        = "name = 'John'";

    private final static
    List<String> LINE_WITH_STRING_PARTS_USING_SIMPLE_QUOTES
        = Arrays.asList("name", "=", "'John'");

    private PulseInstructionLine line;

    private PulseInstructionLine indentedLine;

    private PulseInstructionLine lineWithInvalidIndent;

    @BeforeEach
    void setUp() {
        this.line = new PulseInstructionLine(LINE_CONTENT);
        this.indentedLine = new PulseInstructionLine(INDENTED_LINE_CONTENT);
        this.lineWithInvalidIndent
            = new PulseInstructionLine(LINE_WITH_INVALID_INDENT_CONTENT);
    }

    /**
     * Given: default Pil and its good parts list
     * Then:  Pil object parts and local parts list must
     *        be equals
     * When:  we use PulseInstruction.line.getSplitParts()
     *        non-static method
     */
    @Test
    void getSplitPartsWithGoodParts() {
        List<String> parts;
        parts = new ArrayList<>();
        parts.add("test");
        parts.add("=");
        parts.add("1");

        assertEquals(parts,
                     this.line.getSplitParts(),
                     ERROR_PIL_PARTS_MUST_BE_EQUALS);
    }

    /**
     * Given: Pil with string in its line content with
     *        string using double quotes and its good parts
     *        list as constants
     * Then:  Pil object parts and local parts list must
     *        be equals
     * When:  we use PulseInstruction.line.getSplitParts()
     *        non-static method
     */
    @Test
    void getSplitPartsWithGoodPartsWithStringUsingDoubleQuotes() {
        PulseInstructionLine lineWithString;

        lineWithString = new PulseInstructionLine(
            LINE_CONTENT_WITH_STRING_USING_DOUBLE_QUOTES);

        assertEquals(LINE_WITH_STRING_PARTS_USING_DOUBLE_QUOTES,
                     lineWithString.getSplitParts(),
                     ERROR_PIL_PARTS_MUST_BE_EQUALS);
    }

    /**
     * Given: Pil with string in its line content with
     *        string using simple quotes and its good parts
     *        list as constants
     * Then:  Pil object parts and local parts list must
     *        be equals
     * When:  we use PulseInstruction.line.getSplitParts()
     *        non-static method
     */
    @Test
    void getSplitPartsWithGoodPartsWithStringUsingSimpleQuotes() {
        PulseInstructionLine lineWithString;

        lineWithString = new PulseInstructionLine(
            LINE_CONTENT_WITH_STRING_USING_SIMPLE_QUOTES);

        assertEquals(LINE_WITH_STRING_PARTS_USING_SIMPLE_QUOTES,
                     lineWithString.getSplitParts(),
                     ERROR_PIL_PARTS_MUST_BE_EQUALS);
    }

    /**
     * Given: default Pil and a different parts list
     * Then:  Pil object parts and local parts list must
     *        be different
     * When:  we use PulseInstruction.line.getSplitParts()
     *        non-static method
     */
    @Test
    void getSplitPartsWithWrongParts() {
        List<String> parts;
        parts = new ArrayList<>();
        parts.add("number");
        parts.add("=");
        parts.add("1000");

        assertNotEquals(parts,
                        this.line.getSplitParts(),
                        ERROR_PIL_PARTS_MUST_BE_DIFFERENT);
    }

    /**
     * Given: default indented Pil and its good indent size
     *        as constant
     * Then:  constant and Pil getIndentSize() result must
     *        be equals
     * When:  we use PulseInstructionLine.getIndentSize()
     *        non-static method
     */
    @Test
    void getIndentSizeWithGoodSize() {
        final int GOOD_SIZE = 8;

        int obtainedSize;
        obtainedSize = this.indentedLine.getIndentSize();

        assertEquals(GOOD_SIZE, obtainedSize, ERROR_PIL_INDENT_SIZE_IS_RIGHT);
    }

    /**
     * Given: default indented Pil and a wrong indent size
     *        as constant
     * Then:  constant and Pil getIndentSize() result must
     *        not be equals
     * When:  we use PulseInstructionLine.getIndentSize()
     *        non-static method
     */
    @Test
    void getIndentSizeWithWrongSize() {
        final int GOOD_SIZE = 10;

        int obtainedSize;
        obtainedSize = this.indentedLine.getIndentSize();

        assertNotEquals(GOOD_SIZE,
                        obtainedSize,
                        ERROR_PIL_INDENT_SIZE_IS_WRONG);
    }

    /**
     * Given: default indented Pil and its good indent level
     *        as constant
     * Then:  constant and Pil getIndentLevel() result must
     *        be equals
     * When:  we use PulseInstructionLine.getIndentLevel()
     *        non-static method
     */
    @Test
    void getIndentLevelWithGoodLevel()
    throws PulseInvalidIndentLevelException {
        final int GOOD_LEVEL = 2;

        int obtainedLevel;
        obtainedLevel = this.indentedLine.getIndentLevel();

        assertEquals(GOOD_LEVEL,
                     obtainedLevel,
                     ERROR_PIL_INDENT_LEVEL_IS_RIGHT);
    }

    /**
     * Given: default indented Pil and a wrong indent level
     *        as constant
     * Then:  constant and Pil getIndentLevel() result must
     *        not be equals
     * When:  we use PulseInstructionLine.getIndentLevel()
     *        non-static method
     */
    @Test
    void getIndentLevelWithWrongLevel()
    throws PulseInvalidIndentLevelException {
        final int WRONG_LEVEL = 3;

        int obtainedLevel;
        obtainedLevel = this.indentedLine.getIndentLevel();

        assertNotEquals(WRONG_LEVEL,
                        obtainedLevel,
                        ERROR_PIL_INDENT_LEVEL_IS_WRONG);
    }

    /**
     * Given: default indented Pil and a wrong indent level
     *        as constant
     * Then:  constant and Pil getIndentLevel() result must
     *        not be equals
     * When:  we use PulseInstructionLine.getIndentLevel()
     *        non-static method
     */
    @Test
    void getIndentLevelWithInvalidIndent() {
        assertThrows(PulseInvalidIndentLevelException.class, () -> {
            this.lineWithInvalidIndent.getIndentLevel();
        }, ERROR_PIL_INDENT_LEVEL_IS_INVALID);
    }

    /**
     * Given: default indented Pil
     * Then:  Pil indent must be approved
     * When:  we use PulseInstructionLine.isIndentValid()
     *        non-static method
     */
    @Test
    void isIndentValidWithGoodIndent() {
        assertTrue(this.indentedLine.isIndentValid(),
                   ERROR_PIL_INDENT_IS_VALID);
    }

    /**
     * Given: Pil with invalid indent
     * Then:  Pil indent must be disapproved
     * When:  we use PulseInstructionLine.isIndentValid()
     *        non-static method
     */
    @Test
    void isIndentValidWithWrongIndent() {
        assertFalse(this.lineWithInvalidIndent.isIndentValid(),
                    ERROR_PIL_INDENT_IS_INVALID);
    }

    /**
     * Given: default Pulse instruction line and local Pil
     * Then:  default Pulse instruction line and local Pil
     *        must be equals
     * When:  we use PulseInstructionLine.equals()
     *        non-static method
     */
    @Test
    void linesEquality() {
        PulseInstructionLine line;
        line = new PulseInstructionLine(LINE_CONTENT);

        assertEquals(line, this.line, ERROR_PIL_MUST_BE_EQUALS);
    }

    /**
     * Given: default Pulse instruction line
     * Then:  default and local Pils lines must be equals
     * When:  we use PulseInstructionLine.equals()
     *        non-static method
     */
    @Test
    void getLineWithRightLine() {
        assertEquals(LINE_CONTENT,
                     this.line.getContent(),
                     ERROR_PILS_CONTENT_MUST_BE_EQUALS);
    }

    /**
     * Given: default Pil
     * Then:  default and local Pils lines must be
     *        different
     * When:  we use PulseInstruction.line.equals()
     *        non-static method
     */
    @Test
    void getLineWithWrongLine() {
        final String WRONG_LINE_CONTENT
            = "azerty!";

        assertNotEquals(WRONG_LINE_CONTENT,
                        this.line.getContent(),
                        ERROR_PILS_CONTENT_MUST_BE_DIFFERENT);
    }

    /**
     * Given: two same Pil objects
     * Then:  both Pils must be equals
     * When:  we use PulseInstruction.line.equals()
     *        non-static method
     */
    @Test
    void equalsWithSameObject() {
        PulseInstructionLine line1,
                             line2;

        line1 = new PulseInstructionLine(LINE_CONTENT);
        line2 = line1;

        assertEquals(line1, line2, ERROR_PIL_MUST_BE_EQUALS);
    }

    /**
     * Given: local declared Pil and null local Pil object
     * Then:  both objects must be different
     * When:  we use PulseInstruction.line.equals()
     *        non-static method
     */
    @Test
    void equalsWithNullObject() {
        PulseInstructionLine line1,
                             line2;

        line1 = new PulseInstructionLine(LINE_CONTENT);
        line2 = null;

        assertNotEquals(line1, line2, ERROR_PIL_MUST_BE_DIFFERENT);
    }

    /**
     * Given: local Pil object and PulseFile object
     *        (different classes)
     * Then:  both objects must be different
     * When:  we use PulseInstruction.line.equals()
     *        non-static method
     */
    @Test
    void equalsWithAnotherClassObject() {
        PulseInstructionLine line;

        PulseFile file;

        line = new PulseInstructionLine(LINE_CONTENT);
        file = new PulseFile(PulseTestLocalEnv.GOOD_PULSE_FILE_PATH);

        assertNotEquals(line, file, ERROR_OBJECTS_HAS_NOT_SAME_CLASS_NAME);
    }
}