package dev.practice.imports.service;

import dev.practice.imports.domain.ImportMode;
import dev.practice.imports.domain.ImportResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CsvImportService {
    public static final long MAX_BYTES = 2L * 1024 * 1024;
    public static final int MAX_ROWS = 10_000;

    /** DATA-01: parse quoted CSV correctly; do not use String.split(",") as a CSV parser. */
    public ImportResult importFile(MultipartFile file, ImportMode mode) {
        // TODO: bound bytes/rows, validate headers and each row, detect duplicates, and return safe row errors.
        // ALL_OR_NOTHING must make persistence atomic; PARTIAL_SUCCESS must document which rows were committed.
        throw new UnsupportedOperationException("TODO: implement DATA-01 import");
    }
}
