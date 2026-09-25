package dev.practice.upload.domain;

import java.time.Instant;
import java.util.UUID;

/** Persist metadata only; store file bytes through an isolated storage adapter. */
public record UploadMetadata(UUID storageId, String originalName, String contentType, long size, Instant createdAt) {
}
