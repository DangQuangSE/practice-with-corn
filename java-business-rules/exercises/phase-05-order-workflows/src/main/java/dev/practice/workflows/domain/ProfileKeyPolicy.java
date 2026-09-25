package dev.practice.workflows.domain;

import java.text.Normalizer;
import java.util.Locale;

public class ProfileKeyPolicy {
    /** FLOW-01: normalize identifiers consistently before checking uniqueness. */
    public String normalizeEmail(String email) {
        // TODO: define trimming, Unicode normalization, and case policy before persistence lookup.
        return Normalizer.normalize(email.trim(), Normalizer.Form.NFC).toLowerCase(Locale.ROOT);
    }
}
