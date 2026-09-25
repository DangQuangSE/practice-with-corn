package dev.practice.realtime.api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class JobUpdateController {
    @MessageMapping("/jobs.update")
    @SendTo("/topic/jobs")
    public JobUpdate acceptLocalDemoUpdate(JobUpdate update) {
        // INT-06: validate/authorize and publish only server-owned job state, not arbitrary client status.
        throw new UnsupportedOperationException("TODO: publish a server-owned job update");
    }
}
