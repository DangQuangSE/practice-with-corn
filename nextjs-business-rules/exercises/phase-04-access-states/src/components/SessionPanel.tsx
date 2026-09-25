"use client";

import { useEffect, useState } from "react";
import { getCsrf, getCurrentUser, type CsrfResponse, type SessionUser } from "@/lib/session-client";

const apiBaseUrl = process.env.NEXT_PUBLIC_API_BASE_URL ?? "http://localhost:8081";

type ViewState = "loading" | "anonymous" | "authenticated" | "forbidden" | "error";

export default function SessionPanel() {
  const [state, setState] = useState<ViewState>("loading");
  const [csrf, setCsrf] = useState<CsrfResponse | null>(null);
  const [user, setUser] = useState<SessionUser | null>(null);

  useEffect(() => {
    let active = true;
    Promise.all([getCsrf(), getCurrentUser()])
      .then(([csrfResponse, session]) => {
        if (!active) return;
        setCsrf(csrfResponse);
        setUser(session.user);
        setState(session.status);
      })
      .catch(() => active && setState("error"));
    return () => { active = false; };
  }, []);

  return (
    <section aria-labelledby="session-heading">
      <h2 id="session-heading">Browser session</h2>
      <p role="status" aria-live="polite">State: {state}</p>
      {user && <p>Signed in as {user.username}</p>}

      <form action={`${apiBaseUrl}/login`} method="post">
        <label htmlFor="username">Username</label>
        <input id="username" name="username" autoComplete="username" required />
        <label htmlFor="password">Password</label>
        <input id="password" name="password" type="password" autoComplete="current-password" required />
        <input type="hidden" name={csrf?.parameterName ?? "_csrf"} value={csrf?.token ?? ""} />
        <button type="submit" disabled={!csrf}>Sign in</button>
      </form>

      <form action={`${apiBaseUrl}/api/session/logout`} method="post">
        <input type="hidden" name={csrf?.parameterName ?? "_csrf"} value={csrf?.token ?? ""} />
        <button type="submit" disabled={!csrf || state !== "authenticated"}>Sign out</button>
      </form>
      <p role="alert">TODO: distinguish 401 from 403 and provide a retry/re-auth action.</p>
    </section>
  );
}
