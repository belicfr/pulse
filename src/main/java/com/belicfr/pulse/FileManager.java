/*
 * pulse - FileManager
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse;

import com.belicfr.pulse.exceptions.*;
import com.belicfr.pulse.file.PulseFile;
import com.belicfr.pulse.file.PulseInstructionLine;
import com.belicfr.pulse.lang.LineReader;

import java.io.File;

/**
 * Pulse system file manager.
 * <p>
 * Allows to easily open Pulse source code files.
 *
 * @author belicfr
 */
public class FileManager {
    /**
     * Attempts to open given Pulse source code file by
     * its path.
     *
     * @param path Pulse source code file path
     * @return PulseFile object
     * @throws PulseFileNotFoundException If given path
     *                                    does no longer
     *                                    exist
     */
    public static PulseFile getPulseFile(String path)
    throws PulseFileNotFoundException, PulseUnreadableFileException {
        File file;
        file = new File(path);

        if (!file.exists()) {
            throw new PulseFileNotFoundException(path);
        }

        return new PulseFile(path);
    }

    public static PulseFile runPulseFile(String path)
    throws PulseFileNotFoundException,
           PulseUnreadableFileException, PulseInvalidValueTypeException,
           PulseCannotStoreAsGivenTypeException,
           PulseInvalidInstructionException {

        PulseFile file;
        LineReader reader;

        file = getPulseFile(path);

        for (PulseInstructionLine line: file.getCode(true)) {
            reader = new LineReader(file.getHeap(), line);
            reader.read();
        }

        return file;

    }
}
