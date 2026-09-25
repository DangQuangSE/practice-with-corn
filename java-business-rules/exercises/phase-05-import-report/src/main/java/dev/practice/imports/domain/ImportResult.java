package dev.practice.imports.domain;

import java.util.List;

public record ImportResult(int accepted, int rejected, ImportMode mode, List<RowError> errors) {
}
