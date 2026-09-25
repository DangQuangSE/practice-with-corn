package dev.practice.integrations.oauth;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!external-oauth")
public class DevelopmentIdentityProvider implements OAuthIdentityProvider {
    @Override
    public ExternalIdentity verifyDevelopmentCode(String code) {
        // SEC-09: accept only a deterministic local fixture; never treat this as real OAuth validation.
        if (!"practice-user".equals(code)) {
            throw new IllegalArgumentException("Invalid development authorization code");
        }
        return new ExternalIdentity("synthetic-subject", "practice@example.invalid");
    }
}
