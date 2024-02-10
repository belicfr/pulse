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
import java.util.List;

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
           PulseUnreadableFileException,
           PulseInvalidValueTypeException,
           PulseCannotStoreAsGivenTypeException,
           PulseInvalidInstructionException,
           PulseInvalidIndentLevelException,
           PulseAttemptToGetFunctionValueException {

        PulseInstructionLine currentLine;

        List<PulseInstructionLine> fileLines;

        PulseFile file;
        LineReader reader;

        file = getPulseFile(path);
        fileLines = file.getCode(false);

        for (int lineIndex = 0;
             lineIndex < fileLines.size();
             lineIndex++) {

            currentLine = fileLines.get(lineIndex);
            currentLine.setLineNumber(lineIndex + 1);

            if (!currentLine.getContent().isBlank()) {
                reader = new LineReader(file.getHeap(), currentLine);
                reader.read();
            }

        }

        return file;

    }
}
