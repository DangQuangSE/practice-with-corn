package dev.practice.integrations.webhook;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.HexFormat;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class WebhookSignatureVerifier {
    public boolean verifyHmacSha256(byte[] secret, byte[] canonicalPayload, String suppliedHex) {
        try {
            byte[] supplied = HexFormat.of().parseHex(suppliedHex);
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret, "HmacSHA256"));
            byte[] expected = mac.doFinal(canonicalPayload);
            return MessageDigest.isEqual(expected, supplied);
        } catch (IllegalArgumentException | GeneralSecurityException ex) {
            return false;
        }
    }
}
