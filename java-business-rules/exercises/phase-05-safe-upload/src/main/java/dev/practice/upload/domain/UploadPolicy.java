package dev.practice.upload.domain;

import java.util.Set;

public class UploadPolicy {
    public static final long MAX_BYTES = 5L * 1024 * 1024;
    private static final Set<String> ALLOWED_TYPES = Set.of("image/jpeg", "image/png", "application/pdf");

    /** FILE-01: the declared MIME type is untrusted; compare it with content/magic bytes and extension policy. */
    public void validate(long size, String declaredContentType, byte[] prefix) {
        if (size <= 0 || size > MAX_BYTES) {
            throw new IllegalArgumentException("File size is outside the allowed range");
        }
        if (!ALLOWED_TYPES.contains(declaredContentType)) {
            throw new IllegalArgumentException("File content type is not allowed");
        }
        // TODO: inspect a bounded byte prefix; do not trust the browser-provided MIME type alone.
        throw new UnsupportedOperationException("TODO: validate content signature");
    }
}
