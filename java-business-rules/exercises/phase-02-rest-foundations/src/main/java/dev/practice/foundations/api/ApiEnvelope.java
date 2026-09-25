package dev.practice.foundations.api;

public record ApiEnvelope<T>(T data, PageMetadata page) {
    public record PageMetadata(int number, int size, long totalElements, int totalPages) {
    }
}
