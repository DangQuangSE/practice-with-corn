package dev.practice.imports.domain;

public record RowError(long rowNumber, String field, String safeMessage) {
}
