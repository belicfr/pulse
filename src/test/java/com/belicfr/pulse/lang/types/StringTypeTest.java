/*
 * pulse - StringTypeTest
 * 02-10-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.lang.types;

import com.belicfr.pulse.exceptions.PulseCannotStoreAsGivenTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringTypeTest {
    private static final String ERROR_MUST_BE_COMPATIBLE
        = "Value is compatible with Pulse string type.";

    private static final String ERROR_MUST_NOT_BE_COMPATIBLE
        = "Value is incompatible with Pulse string type.";

    private static final String ERROR_MUST_BE_THROWN_EXCEPTION
        = "Exception must be thrown.";

    private static final String ERROR_MUST_NOT_BE_THROWN_EXCEPTION
        = "Exception must not be thrown.";

    private static final String ERROR_VALUES_MUST_BE_EQUAL
        = "Original value and Pulse string value must be equal.";

    private String compatibleValueWithDoubleQuotes;

    private String compatibleValueWithSimpleQuotes;

    private String incompatibleValue;

    @BeforeEach
    void setUp() {
        this.compatibleValueWithDoubleQuotes = "\"compatible string!\"";
        this.compatibleValueWithSimpleQuotes = "'compatible string!'";
        this.incompatibleValue               = "incompatible string!";
    }

    @Test
    void isCompatibleWithCompatibleValueUsingDoubleQuotes() {
        assertTrue(
            StringType.isCompatible(this.compatibleValueWithDoubleQuotes),
            ERROR_MUST_BE_COMPATIBLE);
    }

    @Test
    void isCompatibleWithCompatibleValueUsingSimpleQuotes() {
        assertTrue(
            StringType.isCompatible(this.compatibleValueWithSimpleQuotes),
            ERROR_MUST_BE_COMPATIBLE);
    }

    @Test
    void isCompatibleWithIncompatibleValue() {
        assertFalse(StringType.isCompatible(this.incompatibleValue),
                    ERROR_MUST_NOT_BE_COMPATIBLE);
    }

    @Test
    void createPulseStringWithCompatibleValueUsingDoubleQuotes() {
        assertDoesNotThrow(() -> {
            new StringType(this.compatibleValueWithDoubleQuotes);
        }, ERROR_MUST_NOT_BE_THROWN_EXCEPTION);
    }

    @Test
    void createPulseStringWithCompatibleValueUsingSimpleQuotes() {
        assertDoesNotThrow(() -> {
            new StringType(this.compatibleValueWithSimpleQuotes);
        }, ERROR_MUST_NOT_BE_THROWN_EXCEPTION);
    }

    @Test
    void createPulseStringWithIncompatibleValue() {
        assertThrows(PulseCannotStoreAsGivenTypeException.class,
                     () -> new StringType(this.incompatibleValue),
                     ERROR_MUST_BE_THROWN_EXCEPTION);
    }

    @Test
    void getValueUsingValueWithDoubleQuotes()
    throws PulseCannotStoreAsGivenTypeException {
        StringType pulseString;
        pulseString = new StringType(this.compatibleValueWithDoubleQuotes);

        assertEquals(pulseString.getValue(),
                     this.compatibleValueWithDoubleQuotes,
                     ERROR_VALUES_MUST_BE_EQUAL);
    }

    @Test
    void getValueUsingValueWithSimpleQuotes()
    throws PulseCannotStoreAsGivenTypeException {
        StringType pulseString;
        pulseString = new StringType(this.compatibleValueWithSimpleQuotes);

        assertEquals(pulseString.getValue(),
                     this.compatibleValueWithSimpleQuotes,
                     ERROR_VALUES_MUST_BE_EQUAL);
    }
}