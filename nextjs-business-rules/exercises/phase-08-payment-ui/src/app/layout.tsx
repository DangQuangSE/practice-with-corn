import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "Phase 08 — Fake payment lifecycle",
  description: "Practice safe provider-neutral payment states",
};

export default function RootLayout({ children }: Readonly<{ children: React.ReactNode }>) {
  return <html lang="vi"><body><main>{children}</main></body></html>;
}
