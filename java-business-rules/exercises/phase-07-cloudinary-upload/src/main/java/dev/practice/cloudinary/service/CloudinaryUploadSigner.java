package dev.practice.cloudinary.service;

import dev.practice.cloudinary.api.SignedUploadParameters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CloudinaryUploadSigner {
    private final String cloudName;
    private final String apiKey;
    private final String apiSecret;

    public CloudinaryUploadSigner(@Value("${cloudinary.cloud-name:}") String cloudName,
                                  @Value("${cloudinary.api-key:}") String apiKey,
                                  @Value("${cloudinary.api-secret:}") String apiSecret) {
        this.cloudName = cloudName;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public SignedUploadParameters createParameters(String validatedPublicId) {
        // INT-03: validate FILE-01 policy first, sign only bounded fields server-side,
        // and return a short-lived request. Never serialize apiSecret to the browser.
        throw new UnsupportedOperationException("TODO: implement provider-verified Cloudinary signature");
    }
}
