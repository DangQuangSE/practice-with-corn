const apiBaseUrl = process.env.NEXT_PUBLIC_API_BASE_URL ?? "http://localhost:8081";

export type CsrfResponse = { headerName: string; parameterName: string; token: string };
export type SessionUser = { username: string };
export type SessionResult = {
  status: "anonymous" | "authenticated" | "forbidden";
  user: SessionUser | null;
};

export async function getCsrf(): Promise<CsrfResponse> {
  const response = await fetch(`${apiBaseUrl}/api/csrf`, {
    credentials: "include",
    cache: "no-store",
  });
  if (!response.ok) throw new Error("Unable to obtain CSRF token");
  return response.json() as Promise<CsrfResponse>;
}

export async function getCurrentUser(): Promise<SessionResult> {
  const response = await fetch(`${apiBaseUrl}/api/me`, {
    credentials: "include",
    cache: "no-store",
  });
  if (response.status === 401) return { status: "anonymous", user: null };
  if (response.status === 403) return { status: "forbidden", user: null };
  if (!response.ok) throw new Error(`Session request failed: ${response.status}`);
  return { status: "authenticated", user: await response.json() as SessionUser };
}
