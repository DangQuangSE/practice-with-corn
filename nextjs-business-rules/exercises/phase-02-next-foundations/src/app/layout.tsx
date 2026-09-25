import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "Phase 02 — Next.js Foundations",
  description: "Independent App Router and Java API contract exercises",
};

export default function RootLayout({ children }: Readonly<{ children: React.ReactNode }>) {
  return (
    <html lang="vi">
      <body>
        <header className="site-header">
          <a href="/" className="brand">Next.js Foundations</a>
          <nav aria-label="Điều hướng chính">
            <a href="/">Tổng quan</a>
            <a href="/items">Items</a>
          </nav>
        </header>
        <main>{children}</main>
      </body>
    </html>
  );
}
