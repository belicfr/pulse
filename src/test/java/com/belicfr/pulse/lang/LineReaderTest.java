/*
 * pulse - LineReaderTest
 * 02-09-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.lang;

import com.belicfr.pulse.exceptions.PulseInvalidInstructionException;
import com.belicfr.pulse.exceptions.PulseInvalidValueTypeException;
import com.belicfr.pulse.file.PulseInstructionLine;
import com.belicfr.pulse.heap.Heap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineReaderTest {
    private static final String ERROR_MUST_NO_THROW_EXCEPTION
        = "None exception must be thrown.";

    private static final String ERROR_MUST_THROW_EXCEPTION
        = "Exception must be thrown.";

    private static final String ERROR_HEAP_REGISTER_MUST_CONTAINS_KEY
        = "Heap register must contains variable name as key.";

    private static final String ERROR_VALUES_MUST_BE_EQUALS
        = "Values must be equals.";

    private PulseInstructionLine invalidInstructionLine;

    private PulseInstructionLine variableDefinitionLineWithIntegerValue;

    private PulseInstructionLine variableDefinitionLineWithStringValue;

    private PulseInstructionLine variableDefinitionLineWithBooleanValue;

    private PulseInstructionLine variableDefinitionLineWithInvalidValue;

    private PulseInstructionLine variableDefinitionLineWithInvalidInstruction;

    @BeforeEach
    void setUp() {
        this.invalidInstructionLine
            = new PulseInstructionLine("gjyijihyijyjh:)");

        this.variableDefinitionLineWithIntegerValue
            = new PulseInstructionLine("age = 19");

        this.variableDefinitionLineWithStringValue
            = new PulseInstructionLine("name = \"John Doe\"");

        this.variableDefinitionLineWithBooleanValue
            = new PulseInstructionLine("state = true");

        this.variableDefinitionLineWithInvalidValue
            = new PulseInstructionLine("invalidValue = no___var");

        this.variableDefinitionLineWithInvalidInstruction
            = new PulseInstructionLine("invalidInstruction = (p");
    }

    @Test
    void readWithInvalidInstructionLine() {
        Heap heap;
        LineReader reader;

        heap = new Heap();

        reader = new LineReader(
            heap, this.invalidInstructionLine);

        assertThrows(PulseInvalidInstructionException.class,
                     reader::read,
                     ERROR_MUST_THROW_EXCEPTION);
    }

    @Test
    void readWithVariableDefinitionInstructionWithIntegerValue() {
        Heap heap;
        LineReader reader;
        String variableKey,
               variableValue;

        heap = new Heap();

        reader = new LineReader(heap,
                                this.variableDefinitionLineWithIntegerValue);

        variableKey = this.variableDefinitionLineWithIntegerValue
                          .getSplitParts()
                          .get(0);

        variableValue = this.variableDefinitionLineWithIntegerValue
                            .getSplitParts()
                            .get(2);

        assertDoesNotThrow(reader::read, ERROR_MUST_NO_THROW_EXCEPTION);

        assertTrue(heap.getRegister()
                       .containsKey(variableKey),
                   ERROR_HEAP_REGISTER_MUST_CONTAINS_KEY);

        assertEquals(heap.getRegister()
                         .get(variableKey)
                         .getExpression(),
                     variableValue,
                     ERROR_VALUES_MUST_BE_EQUALS);
    }

    @Test
    void readWithVariableDefinitionInstructionWithStringValue() {
        Heap heap;
        LineReader reader;
        String variableKey,
            variableValue;

        heap = new Heap();

        reader = new LineReader(heap,
                                this.variableDefinitionLineWithStringValue);

        variableKey = this.variableDefinitionLineWithStringValue
            .getSplitParts()
            .get(0);

        variableValue = this.variableDefinitionLineWithStringValue
            .getSplitParts()
            .get(2);

        assertDoesNotThrow(reader::read, ERROR_MUST_NO_THROW_EXCEPTION);

        assertTrue(heap.getRegister()
                       .containsKey(variableKey),
                   ERROR_HEAP_REGISTER_MUST_CONTAINS_KEY);

        assertEquals(heap.getRegister()
                         .get(variableKey)
                         .getExpression(),
                     variableValue,
                     ERROR_VALUES_MUST_BE_EQUALS);
    }

    @Test
    void readWithVariableDefinitionInstructionWithBooleanValue() {
        Heap heap;
        LineReader reader;
        String variableKey,
            variableValue;

        heap = new Heap();

        reader = new LineReader(heap,
                                this.variableDefinitionLineWithBooleanValue);

        variableKey = this.variableDefinitionLineWithBooleanValue
            .getSplitParts()
            .get(0);

        variableValue = this.variableDefinitionLineWithBooleanValue
            .getSplitParts()
            .get(2);

        assertDoesNotThrow(reader::read, ERROR_MUST_NO_THROW_EXCEPTION);

        assertTrue(heap.getRegister()
                       .containsKey(variableKey),
                   ERROR_HEAP_REGISTER_MUST_CONTAINS_KEY);

        assertEquals(heap.getRegister()
                         .get(variableKey)
                         .getExpression(),
                     variableValue,
                     ERROR_VALUES_MUST_BE_EQUALS);
    }

    @Test
    void readWithVariableDefinitionInstructionWithInvalidValue() {
        Heap heap;
        LineReader reader;

        heap = new Heap();

        reader = new LineReader(heap,
                                this.variableDefinitionLineWithInvalidValue);

        assertThrows(PulseInvalidValueTypeException.class,
                     reader::read,
                     ERROR_MUST_THROW_EXCEPTION);
    }

    @Test
    void readWithVariableDefinitionInstructionWithInvalidInstruction() {
        Heap heap;
        LineReader reader;

        heap = new Heap();

        reader = new LineReader(
            heap, this.variableDefinitionLineWithInvalidInstruction);

        assertThrows(PulseInvalidInstructionException.class,
                     reader::read,
                     ERROR_MUST_THROW_EXCEPTION);
    }

    @Test
    void getFileHeap() {
    }

    @Test
    void getLine() {
    }
}