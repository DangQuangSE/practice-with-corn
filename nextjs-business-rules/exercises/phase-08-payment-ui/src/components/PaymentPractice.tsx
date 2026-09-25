"use client";

import { useState } from "react";

type Status = "PENDING" | "SUCCEEDED" | "FAILED" | "CANCELLED" | "REFUNDED";
type Payment = { paymentId: string; orderId: string; amountVnd: number; currency: string; status: Status };

const sampleOrder = { orderId: "practice-order-101", amountVnd: 125_000, currency: "VND" };

export function PaymentPractice() {
  const [payment, setPayment] = useState<Payment | null>(null);
  const [message, setMessage] = useState("No payment request yet.");

  function createFakePayment() {
    const next: Payment = {
      paymentId: `fake-${crypto.randomUUID()}`,
      orderId: sampleOrder.orderId,
      amountVnd: sampleOrder.amountVnd,
      currency: sampleOrder.currency,
      status: "PENDING",
    };
    setPayment(next);
    setMessage("Fake checkout created. The authoritative state is still PENDING.");
    // TODO PAY-01: POST order ID with Idempotency-Key; Java loads ownership/amount and persists pending state.
  }

  function simulateVerifiedFakeCallback(status: "SUCCEEDED" | "FAILED" | "CANCELLED") {
    if (!payment) return;
    setPayment({ ...payment, status });
    setMessage(`Local practice event: ${status}. In real integration, only a verified server callback updates this state.`);
    // TODO: call a dev-only fake callback endpoint; never add this control to a real provider build.
  }

  function handleBrowserReturn() {
    if (!payment) return;
    const url = new URL(window.location.href);
    url.searchParams.set("paymentId", payment.paymentId);
    url.searchParams.set("status", "paid"); // Deliberately untrusted demonstration input.
    window.history.replaceState(null, "", `${url.pathname}${url.search}`);
    setMessage("Browser says 'paid', but UI keeps the last backend state. TODO: refetch GET /api/payments/{id}.");
  }

  return <section aria-labelledby="payment-heading">
    <h2 id="payment-heading">Synthetic order</h2>
    <dl>
      <dt>Order</dt><dd>{sampleOrder.orderId}</dd>
      <dt>Amount</dt><dd>{sampleOrder.amountVnd.toLocaleString("vi-VN")} {sampleOrder.currency}</dd>
    </dl>
    <button type="button" onClick={createFakePayment}>Create local fake payment</button>
    {payment && <>
      <h3>Payment status: {payment.status}</h3>
      <p>Reference: {payment.paymentId}</p>
      <p className="notice" role="status" aria-live="polite">{message}</p>
      <div>
        <button type="button" onClick={handleBrowserReturn}>Simulate browser return with status=paid</button>
        <button type="button" onClick={() => simulateVerifiedFakeCallback("SUCCEEDED")}>Simulate verified fake success</button>
        <button type="button" onClick={() => simulateVerifiedFakeCallback("FAILED")}>Simulate fake failure</button>
        <button type="button" onClick={() => simulateVerifiedFakeCallback("CANCELLED")}>Simulate fake cancel</button>
      </div>
    </>}
    <p className="muted">PAY-02 TODO: loading, duplicate request, unavailable provider, refreshed return, and authorized ownership states.</p>
  </section>;
}
