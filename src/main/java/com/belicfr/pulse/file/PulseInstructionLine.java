/*
 * pulse - PulseInstructionLine
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

/*
 * pulse - PulseInstructionLine
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.file;

import java.util.Objects;

public class PulseInstructionLine {
    private String line;

    public PulseInstructionLine(String line) {
        this.line = line;

        // TODO: create pulse line attributes.
    }

    /**
     * @return Line content
     */
    public String getLine() {
        return this.line;
    }

    @Override
    public boolean equals(Object o) {
        PulseInstructionLine that;

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        that = (PulseInstructionLine) o;

        return Objects.equals(this.line, that.line);
    }

    @Override
    public int hashCode() {
        return Objects.hash(line);
    }
}
