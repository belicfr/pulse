/*
 * pulse - Heap
 * 02/09/2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.heap;

import com.belicfr.pulse.lang.types.Type;
import com.belicfr.pulse.lang.types.TypeInterface;

import java.util.HashMap;

public class Heap {
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
}
