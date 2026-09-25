package dev.practice.payments.api;

import dev.practice.payments.domain.PaymentSummary;
import dev.practice.payments.service.PaymentCallbackProcessor;
import dev.practice.payments.service.PaymentWorkflowService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PaymentController {
    private static final int MAX_WEBHOOK_BYTES = 64 * 1024;

    private final PaymentWorkflowService payments;
    private final PaymentCallbackProcessor callbacks;

    public PaymentController(PaymentWorkflowService payments, PaymentCallbackProcessor callbacks) {
        this.payments = payments;
        this.callbacks = callbacks;
    }

    @PostMapping("/orders/{orderId}/payments")
    public ResponseEntity<PaymentSummary> start(@PathVariable String orderId,
                                                @RequestHeader("Idempotency-Key") String idempotencyKey) {
        // TODO: obtain authenticated account ID from the Security principal, not the request body.
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<PaymentSummary> status(@PathVariable String paymentId) {
        // TODO: check ownership and return the authoritative backend state.
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping("/payments/webhooks/{provider}")
    public ResponseEntity<Void> webhook(@PathVariable String provider,
                                        @RequestHeader(value = "X-Provider-Signature", required = false) String signature,
                                        HttpServletRequest request) throws IOException {
        if (request.getContentLength() > MAX_WEBHOOK_BYTES) {
            return ResponseEntity.status(HttpStatus.CONTENT_TOO_LARGE).build();
        }
        byte[] rawBody = request.getInputStream().readNBytes(MAX_WEBHOOK_BYTES + 1);
        if (rawBody.length > MAX_WEBHOOK_BYTES) {
            return ResponseEntity.status(HttpStatus.CONTENT_TOO_LARGE).build();
        }
        // TODO: select an allowlisted adapter; do not parse/settle from arbitrary browser parameters.
        // Pass only this size-bounded raw body to the selected provider verifier.
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
