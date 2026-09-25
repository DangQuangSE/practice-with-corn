export type Item = {
  id: string;
  name: string;
  price: number;
};

export type ProblemDetails = {
  type?: string;
  title: string;
  status: number;
  detail?: string;
  instance?: string;
  errors?: Array<{ field: string; message: string }>;
};

type ApiEnvelope<T> = {
  data: T;
  page?: { number: number; size: number; totalElements: number; totalPages: number };
};

const apiBaseUrl = process.env.NEXT_PUBLIC_API_BASE_URL ?? "http://localhost:8080";

export async function getItems(): Promise<Item[]> {
  // TODO FE-03/04/06: choose server/browser call site and explicit cache/revalidation semantics.
  // This URL is public configuration; never add a private API credential here.
  const response = await fetch(`${apiBaseUrl}/api/items`, { cache: "no-store" });
  if (!response.ok) {
    throw new Error(`Items request failed with status ${response.status}`);
  }
  const envelope = await response.json() as ApiEnvelope<Item[]>;
  return envelope.data;
}
