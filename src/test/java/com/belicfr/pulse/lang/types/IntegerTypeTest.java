/*
 * pulse - IntegerTypeTest
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

class IntegerTypeTest {
    private static final String ERROR_MUST_BE_COMPATIBLE
        = "Value is compatible with Pulse integer type.";

    private static final String ERROR_MUST_NOT_BE_COMPATIBLE
        = "Value is incompatible with Pulse integer type.";

    private static final String ERROR_MUST_BE_THROWN_EXCEPTION
        = "Exception must be thrown.";

    private static final String ERROR_MUST_NOT_BE_THROWN_EXCEPTION
        = "Exception must not be thrown.";

    private static final String ERROR_VALUES_MUST_BE_EQUAL
        = "Original value and Pulse string value must be equal.";

    private String positiveCompatibleValue;

    private String negativeCompatibleValue;

    private String incompatibleValue;

    @BeforeEach
    void setUp() {
        this.positiveCompatibleValue = "1";
        this.negativeCompatibleValue = "-5";
        this.incompatibleValue = "not___var";
    }

    @Test
    void isCompatibleWithPositiveCompatibleValue() {
        assertTrue(IntegerType.isCompatible(this.positiveCompatibleValue),
                   ERROR_MUST_BE_COMPATIBLE);
    }

    @Test
    void isCompatibleWithNegativeCompatibleValue() {
        assertTrue(IntegerType.isCompatible(this.negativeCompatibleValue),
                   ERROR_MUST_BE_COMPATIBLE);
    }

    @Test
    void isCompatibleWithIncompatibleValue() {
        assertFalse(IntegerType.isCompatible(this.incompatibleValue),
                    ERROR_MUST_NOT_BE_COMPATIBLE);
    }

    @Test
    void createPulseIntegerWithPositiveCompatibleValue() {
        assertDoesNotThrow(() -> {
            new IntegerType(this.positiveCompatibleValue);
        }, ERROR_MUST_NOT_BE_THROWN_EXCEPTION);
    }

    @Test
    void createPulseIntegerWithNegativeCompatibleValue() {
        assertDoesNotThrow(() -> {
            new IntegerType(this.negativeCompatibleValue);
        }, ERROR_MUST_NOT_BE_THROWN_EXCEPTION);
    }

    @Test
    void createPulseIntegerWithIncompatibleValue() {
        assertThrows(PulseCannotStoreAsGivenTypeException.class,
                     () -> new IntegerType(this.incompatibleValue),
                     ERROR_MUST_BE_THROWN_EXCEPTION);
    }

    @Test
    void getValueWithPositiveValue()
    throws PulseCannotStoreAsGivenTypeException {
        IntegerType pulseInteger;
        pulseInteger = new IntegerType(this.positiveCompatibleValue);

        assertEquals(pulseInteger.getValue(),
                     Integer.parseInt(this.positiveCompatibleValue),
                     ERROR_VALUES_MUST_BE_EQUAL);
    }

    @Test
    void getValueWithNegativeValue()
    throws PulseCannotStoreAsGivenTypeException {
        IntegerType pulseInteger;
        pulseInteger = new IntegerType(this.negativeCompatibleValue);

        assertEquals(pulseInteger.getValue(),
                     Integer.parseInt(this.negativeCompatibleValue),
                     ERROR_VALUES_MUST_BE_EQUAL);
    }
}