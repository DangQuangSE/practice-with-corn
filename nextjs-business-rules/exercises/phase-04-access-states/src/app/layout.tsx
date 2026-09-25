import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "Phase 04 — Access states",
  description: "Cookie-session and API authorization practice",
};

export default function RootLayout({ children }: Readonly<{ children: React.ReactNode }>) {
  return <html lang="vi"><body><main>{children}</main></body></html>;
}
