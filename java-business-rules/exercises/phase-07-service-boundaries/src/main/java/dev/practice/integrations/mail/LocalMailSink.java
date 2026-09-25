package dev.practice.integrations.mail;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!real-mail")
public class LocalMailSink implements MailGateway {
    private final List<MailMessage> messages = new CopyOnWriteArrayList<>();

    @Override
    public void send(MailMessage message) {
        // Synthetic messages only. Do not log tokens or use this sink for real recipients.
        messages.add(message);
    }

    public List<MailMessage> snapshot() {
        return List.copyOf(messages);
    }
}
