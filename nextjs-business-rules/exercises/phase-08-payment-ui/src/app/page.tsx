import { PaymentPractice } from "@/components/PaymentPractice";

export default function Page() {
  return <>
    <p className="muted">Phase 08 · PAY-01–PAY-06</p>
    <h1>Payment lifecycle practice</h1>
    <p>Only synthetic orders and local fake state. No provider account, credential, or money movement.</p>
    <PaymentPractice />
  </>;
}
