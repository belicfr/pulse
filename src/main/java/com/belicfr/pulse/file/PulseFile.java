/*
 * pulse - PulseFile
 * 02-08-2024
 *
 * belicfr
 * https://github.com/belicfr
 * 2024 (c) All rights reserved
 */

package com.belicfr.pulse.file;

import com.belicfr.pulse.exceptions.PulseUnreadableFileException;
import com.belicfr.pulse.heap.Heap;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PulseFile {
    /** File path */
    private String path;

    /** Heap instance associated to current Pulse file */
    private Heap heap;

    public PulseFile(String path) {
        this.path = path;
        this.heap = new Heap();
    }

    /**
     * Attempt to read Pulse file and return its lines.
     *
     * @return Pulse file lines
     * @throws PulseUnreadableFileException If given file cannot be
     *                             read
     */
    private List<String> getFileLines()
    throws PulseUnreadableFileException {
        Path path;

        path = Paths.get(this.getPath());

        try {
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        throw new PulseUnreadableFileException(this.getPath());
    }

    /**
     * @return File path
     */
    public String getPath() {
        return this.path;
    }

    /**
     * @return File content
     * @throws PulseUnreadableFileException If file cannot be read
     */
    public String getContent()
    throws PulseUnreadableFileException {
        StringBuilder content;
        content = new StringBuilder();

        for (String line: this.getFileLines()) {
            content.append(line).append('\n');
        }

        return content.toString();
    }

    /**
     * Reads file and returns a Pulse lines objects list.
     *
     * @return Pulse lines objects list
     * @throws PulseUnreadableFileException If file cannot be read
     */
    public List<PulseInstructionLine> getCode()
    throws PulseUnreadableFileException {
        List<PulseInstructionLine> code;
        code = new ArrayList<>();

        for (String line: this.getFileLines()) {
            code.add(new PulseInstructionLine(line));
        }

        return code;
    }

    /**
     * @return Current heap object
     */
    public Heap getHeap() {
        return this.heap;
    }
}
