/*
 * pulse - BlockType
 * 02-10-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.lang.types;

import com.belicfr.pulse.exceptions.PulseIndentLevelLowException;
import com.belicfr.pulse.exceptions.PulseInvalidIndentLevelException;
import com.belicfr.pulse.file.PulseInstructionLine;

public interface BlockTypeInterface {
    void addToBody(PulseInstructionLine line)
    throws PulseInvalidIndentLevelException,
           PulseIndentLevelLowException;
}
