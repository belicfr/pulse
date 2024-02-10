/*
 * pulse - BooleanTypeTest
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

class BooleanTypeTest {
    private static final String ERROR_MUST_BE_COMPATIBLE
        = "Value is compatible with Pulse boolean type.";

    private static final String ERROR_MUST_NOT_BE_COMPATIBLE
        = "Value is incompatible with Pulse boolean type.";

    private static final String ERROR_MUST_BE_THROWN_EXCEPTION
        = "Exception must be thrown.";

    private static final String ERROR_MUST_NOT_BE_THROWN_EXCEPTION
        = "Exception must not be thrown.";

    private static final String ERROR_VALUES_MUST_BE_EQUAL
        = "Original value and Pulse string value must be equal.";

    private String trueValue;

    private String falseValue;

    private String incompatibleValue;

    @BeforeEach
    void setUp() {
        this.trueValue = "true";
        this.falseValue = "false";
        this.incompatibleValue = "not___var";
    }

    @Test
    void isCompatibleWithTrueValue() {
        assertTrue(BooleanType.isCompatible(this.trueValue),
                   ERROR_MUST_BE_COMPATIBLE);
    }

    @Test
    void isCompatibleWithFalseValue() {
        assertTrue(BooleanType.isCompatible(this.falseValue),
                   ERROR_MUST_BE_COMPATIBLE);
    }

    @Test
    void isCompatibleWithIncompatibleValue() {
        assertFalse(BooleanType.isCompatible(this.incompatibleValue),
                    ERROR_MUST_NOT_BE_COMPATIBLE);
    }

    @Test
    void createPulseBooleanWithPositiveCompatibleValue() {
        assertDoesNotThrow(() -> {
            new BooleanType(this.trueValue);
        }, ERROR_MUST_NOT_BE_THROWN_EXCEPTION);
    }

    @Test
    void createPulseBooleanWithNegativeCompatibleValue() {
        assertDoesNotThrow(() -> {
            new BooleanType(this.falseValue);
        }, ERROR_MUST_NOT_BE_THROWN_EXCEPTION);
    }

    @Test
    void createPulseBooleanWithIncompatibleValue() {
        assertThrows(PulseCannotStoreAsGivenTypeException.class,
                     () -> new BooleanType(this.incompatibleValue),
                     ERROR_MUST_BE_THROWN_EXCEPTION);
    }

    @Test
    void getValueWithTrueValue()
    throws PulseCannotStoreAsGivenTypeException {
        BooleanType pulseBoolean;
        pulseBoolean = new BooleanType(this.trueValue);

        assertEquals(pulseBoolean.getValue(),
                     Boolean.parseBoolean(this.trueValue),
                     ERROR_VALUES_MUST_BE_EQUAL);
    }

    @Test
    void getValueWithFalseValue()
    throws PulseCannotStoreAsGivenTypeException {
        BooleanType pulseBoolean;
        pulseBoolean = new BooleanType(this.falseValue);

        assertEquals(pulseBoolean.getValue(),
                     Boolean.parseBoolean(this.falseValue),
                     ERROR_VALUES_MUST_BE_EQUAL);
    }
}