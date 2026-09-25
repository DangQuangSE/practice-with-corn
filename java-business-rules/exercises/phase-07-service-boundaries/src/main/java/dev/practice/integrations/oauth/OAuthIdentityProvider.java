package dev.practice.integrations.oauth;

public interface OAuthIdentityProvider {
    ExternalIdentity verifyDevelopmentCode(String code);

    record ExternalIdentity(String subject, String email) {
    }
}
