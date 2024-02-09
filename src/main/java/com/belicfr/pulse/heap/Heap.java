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

import java.util.HashMap;
import java.util.List;

public class Heap {
    private HashMap<String, Type> register;

    public Heap() {
        this.register = new HashMap<>();
    }

    /**
     * @return Current heap register
     */
    public HashMap<String, Type> getRegister() {
        return this.register;
    }

    /**
     * Add a new line into current register.
     *
     * @param key
     * @param value
     */
    public void add(String key, Type value) {
        this.register.put(key, value);
    }
}
