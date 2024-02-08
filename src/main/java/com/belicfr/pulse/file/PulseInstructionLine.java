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

import com.belicfr.pulse.exceptions.PulseInvalidIndentLevelException;

import java.util.Objects;

/**
 * This class represents each line of a Pulse source code
 * file.
 * <p>
 * It allows to perform operations specific to these lines.
 *
 * @author belicfr
 */
public class PulseInstructionLine {
    public static final int INDENT_UNIT_SIZE = 4;

    public static final char INDENT_UNIT_CHARACTER = ' ';

    private String line;

    public PulseInstructionLine(String line) {
        this.line = line;
    }

    /**
     * @return Line indent size
     */
    public int getIndentSize() {
        int indentUnitCount;
        indentUnitCount = 0;

        for (char character: this.getLine().toCharArray()) {
            if (character == INDENT_UNIT_CHARACTER) {
                indentUnitCount++;
            } else {
                break;
            }
        }

        return indentUnitCount;
    }

    /**
     * Measures and returns the indent level.
     *
     * @return Indent level
     * @throws PulseInvalidIndentLevelException If indent
     *                                          level is
     *                                          invalid
     */
    public int getIndentLevel()
    throws PulseInvalidIndentLevelException {
        int indentSize;
        indentSize = this.getIndentSize();

        if (!this.isIndentValid()) {
            throw new PulseInvalidIndentLevelException(this.getLine());
        }

        return indentSize / INDENT_UNIT_SIZE;
    }

    /**
     * @return If indent is valid (packs of
     *         INDENT_UNIT_SIZE spaces).
     */
    public boolean isIndentValid() {
        return this.getIndentSize() % INDENT_UNIT_SIZE == 0;
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
