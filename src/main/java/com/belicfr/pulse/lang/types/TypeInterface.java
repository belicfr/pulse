/*
 * pulse - TypeInterface
 * 02-09-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.lang.types;

public interface TypeInterface {
    String getExpression();

    Object getValue();

    static boolean isCompatible() {
        return false;
    }
}
