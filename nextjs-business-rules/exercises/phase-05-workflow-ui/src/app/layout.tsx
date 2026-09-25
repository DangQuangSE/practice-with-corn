import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "Phase 05 — Workflow practice",
  description: "Small frontend exercises for business workflows",
};

export default function RootLayout({ children }: Readonly<{ children: React.ReactNode }>) {
  return <html lang="vi"><body><main>{children}</main></body></html>;
}
