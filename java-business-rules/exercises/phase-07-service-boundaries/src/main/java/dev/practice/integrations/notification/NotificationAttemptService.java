package dev.practice.integrations.notification;

import org.springframework.stereotype.Service;

@Service
public class NotificationAttemptService {
    /** FLOW-10/FLOW-12: persist history, suppress duplicates, cap retries, and expose status to the caller. */
    public DeliveryState deliver(String notificationId) {
        // TODO: claim one pending attempt atomically; record result/failure category without message secrets.
        throw new UnsupportedOperationException("TODO: implement retryable notification lifecycle");
    }
}
