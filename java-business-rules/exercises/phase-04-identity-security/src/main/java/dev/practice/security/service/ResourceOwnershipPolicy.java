package dev.practice.security.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class ResourceOwnershipPolicy {
    public void requireOwner(String authenticatedSubject, String resourceOwner) {
        // TODO SEC-04: define safe behavior for absent/invalid IDs and apply this check in every write/read path.
        if (authenticatedSubject == null || !authenticatedSubject.equals(resourceOwner)) {
            throw new AccessDeniedException("You do not have access to this resource.");
        }
    }
}
