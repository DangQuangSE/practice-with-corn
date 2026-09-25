package dev.practice.security.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenIssuer {
    private final JwtEncoder encoder;

    public AccessTokenIssuer(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String issue(String subject, Collection<String> scopes) {
        // TODO SEC-02: validate the subject/scopes, set issuer/audience, and choose a short bounded expiry.
        // Use Spring/Nimbus claims and encoder APIs; do not implement signing or base64 JWT assembly yourself.
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(now)
                .expiresAt(now.plus(Duration.ofMinutes(15)))
                .claim("scope", String.join(" ", scopes))
                .build();
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        return encoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }
}
