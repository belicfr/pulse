/*
 * pulse - Heap
 * 02/09/2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.heap;

import com.belicfr.pulse.exceptions.PulseAttemptToGetFunctionValueException;
import com.belicfr.pulse.lang.types.BlockTypeInterface;
import com.belicfr.pulse.lang.types.FunctionType;
import com.belicfr.pulse.lang.types.Type;
import com.belicfr.pulse.lang.types.TypeInterface;

import java.util.HashMap;

public class Heap {
    private static final String ENTITY_REPRESENTATION_PATTERN
        = "Pulse:[%s<%s> {%s}]";

    private HashMap<String, TypeInterface> register;

    public Heap() {
        this.register = new HashMap<>();
    }

    /**
     * @return Current heap register
     */
    public HashMap<String, TypeInterface> getRegister() {
        return this.register;
    }

    /**
     * Add a new line into current register.
     *
     * @param key
     * @param value
     */
    public void add(String key, TypeInterface value) {
        this.register.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder heap;
        heap = new StringBuilder();

        this.getRegister()
            .forEach((key, value) -> {
                heap.append(String.format(ENTITY_REPRESENTATION_PATTERN + '\n',
                                          value.getClass().getSimpleName(),
                                          key,
                                          value));
            });

        return heap.toString();
    }
}
