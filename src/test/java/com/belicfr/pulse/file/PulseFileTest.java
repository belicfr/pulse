/*
 * pulse - PulseFileTest
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.file;

import com.belicfr.pulse.FileManager;
import com.belicfr.pulse.PulseTestLocalEnv;
import com.belicfr.pulse.exceptions.PulseFileNotFoundException;
import com.belicfr.pulse.exceptions.PulseUnreadableFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PulseFileTest {
    private static final String ERROR_FILE_MUST_BE_UNREADABLE
        = "File must be unreadable.";

    private static final String ERROR_WRONG_PATH
        = "Paths must be same.";

    private static final String ERROR_UNREADABLE_FILE
        = "File cannot be read.";

    private static final String ERROR_WRONG_FILE_CONTENT
        = "File content does not correspond.";

    private static final String ERROR_WRONG_PULSE_INSTRUCTION_LINES_LIST
        = "Wrong Pulse instruction lines list.";

    private PulseFile file;

    @BeforeEach
    void setUp() {
        try {
            file = FileManager.getPulseFile(PulseTestLocalEnv.GOOD_PULSE_FILE_PATH);
        } catch (PulseFileNotFoundException | PulseUnreadableFileException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void openUndefinedFile() {
        final String WRONG_PATH
            = "azerty";

        assertThrows(PulseFileNotFoundException.class, () -> {
            FileManager.getPulseFile(WRONG_PATH);
        }, ERROR_FILE_MUST_BE_UNREADABLE);
    }

    /**
     * Given: a PulseFile object with PATH constant as path
     * Then:  object path and PATH constant must be
     *        identical
     * When:  we use PulseFile.getPath() non-static method
     */
    @Test
    void getPath() {
        assertEquals(this.file.getPath(),
                     PulseTestLocalEnv.GOOD_PULSE_FILE_PATH,
                     ERROR_WRONG_PATH);
    }

    /**
     * Given: default file
     * Then:  no exception must be thrown and the file
     *        content and CODE constant must be identical
     * When:  we use PulseFile.getContent() non-static
     *        method
     *
     * @throws PulseUnreadableFileException If file is unreadable
     */
    @Test
    void getContent()
    throws PulseUnreadableFileException {
        final String CODE
            = "test = 1\n"
            + "age = 19\n";

        assertDoesNotThrow(() -> {
            this.file.getContent();
        }, ERROR_UNREADABLE_FILE);

        assertEquals(CODE, this.file.getContent(), ERROR_WRONG_FILE_CONTENT);
    }

    /**
     * Given: default file
     * Then:  no exception must be thrown and the file
     *        PulseInstructionLine list must correspond
     *        with LINES constant
     * When:  we use Pulsefile.getCode() non-static method
     */
    @Test
    void getCode()
    throws PulseUnreadableFileException {
        final List<PulseInstructionLine> LINES;
        LINES = new ArrayList<>();
        LINES.add(new PulseInstructionLine("test = 1"));
        LINES.add(new PulseInstructionLine("age = 19"));

        assertDoesNotThrow(() -> {
            this.file.getCode(false);
        }, ERROR_UNREADABLE_FILE);

        assertEquals(LINES,
                     this.file.getCode(false),
                     ERROR_WRONG_PULSE_INSTRUCTION_LINES_LIST);
    }
}