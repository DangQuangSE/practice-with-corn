package dev.practice.cloudinary.api;

import java.time.Instant;

/** Safe fields the browser needs; the API secret is intentionally absent. */
public record SignedUploadParameters(String cloudName, String apiKey, String folder, String publicId,
                                     Instant timestamp, String signature) {
}
