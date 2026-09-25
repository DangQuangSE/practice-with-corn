package dev.practice.integrations.mail;

public interface MailGateway {
    void send(MailMessage message);

    record MailMessage(String recipient, String subject, String text) {
    }
}
