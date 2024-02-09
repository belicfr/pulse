/*
 * pulse - FileManagerTest
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse;

import com.belicfr.pulse.exceptions.PulseFileNotFoundException;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {
    private static final String ERROR_FILE_MUST_BE_UNDEFINED
        = "File must be undefined.";

    private static final String ERROR_FILE_MUST_EXIST
        = "File must exist.";

    /**
     * Given: an undefined Pulse file path
     * Then:  throws a PulseFileNotFoundException
     * When:  we attempt to use FileManager.openFile()
     *        with this path
     */
    @Test
    void openUndefinedFile() {
        final String WRONG_PATH = "azerty";

        assertThrows(PulseFileNotFoundException.class, () -> {
            FileManager.openFile(WRONG_PATH);
        }, ERROR_FILE_MUST_BE_UNDEFINED);
    }

    /**
     * Given: an existing Pulse file path
     * Then:  does not throw an exception
     * When:  we attempt to use FileManager.openFile()
     *        with this path
     */
    @Test
    void openExistingFile() {
        assertDoesNotThrow(() -> {
            FileManager.openFile(PulseTestLocalEnv.GOOD_PULSE_FILE_PATH);
        }, ERROR_FILE_MUST_EXIST);
    }
}