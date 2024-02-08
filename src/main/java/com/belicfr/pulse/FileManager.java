/*
 * pulse - FileManager
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse;

import com.belicfr.pulse.exceptions.PulseFileNotFoundException;
import com.belicfr.pulse.exceptions.PulseUnreadableFileException;
import com.belicfr.pulse.file.PulseFile;

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
    public static PulseFile openFile(String path)
    throws PulseFileNotFoundException, PulseUnreadableFileException {
        File file;
        file = new File(path);

        if (!file.exists()) {
            throw new PulseFileNotFoundException(path);
        }

        return new PulseFile(path);
    }
}
