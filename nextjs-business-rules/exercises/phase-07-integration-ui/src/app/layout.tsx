import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "Phase 07 — Integration UI",
  description: "Practice local upload and realtime integration states",
};

export default function RootLayout({ children }: Readonly<{ children: React.ReactNode }>) {
  return <html lang="vi"><body><main>{children}</main></body></html>;
}
