package dev.practice.integrations.jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledJobRunner {
    @Scheduled(fixedDelayString = "${practice.jobs.poll-delay-ms:30000}")
    public void runBoundedBatch() {
        // JOB-01: claim a bounded batch, persist RUNNING before work, and store COMPLETED/FAILED safely.
        // Design for retries and multiple instances; do not hold a database transaction during slow I/O.
    }
}
