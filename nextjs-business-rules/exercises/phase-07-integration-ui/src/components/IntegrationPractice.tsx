"use client";

import { Client, IMessage } from "@stomp/stompjs";
import { ChangeEvent, useEffect, useState } from "react";
import { uploadWithProgress } from "@/lib/upload-client";

type JobUpdate = { jobId: string; status: string; completedItems: number; occurredAt: string };

export function IntegrationPractice() {
  const [connection, setConnection] = useState("disconnected");
  const [events, setEvents] = useState<JobUpdate[]>([]);
  const [uploadMessage, setUploadMessage] = useState("No file uploaded");
  const [progress, setProgress] = useState(0);

  useEffect(() => {
    const brokerURL = process.env.NEXT_PUBLIC_STOMP_URL;
    if (!brokerURL) {
      setConnection("missing local STOMP URL");
      return;
    }
    const client = new Client({
      brokerURL,
      reconnectDelay: 3000,
      onConnect: () => {
        setConnection("connected");
        client.subscribe("/topic/jobs", (message: IMessage) => {
          try {
            const update = JSON.parse(message.body) as JobUpdate;
            setEvents((current) => [update, ...current].slice(0, 10));
          } catch {
            setConnection("received invalid event");
          }
        });
      },
      onWebSocketClose: () => setConnection("disconnected; reconnecting"),
      onStompError: () => setConnection("broker error"),
    });
    client.activate();
    return () => { void client.deactivate(); };
  }, []);

  async function onFile(event: ChangeEvent<HTMLInputElement>) {
    const file = event.target.files?.[0];
    if (!file) return;
    if (file.size > 5 * 1024 * 1024) {
      setUploadMessage("File must be 5 MiB or smaller.");
      return;
    }
    setProgress(0);
    setUploadMessage("Uploading…");
    try {
      const result = await uploadWithProgress(file, (next) => setProgress(next.percent));
      setUploadMessage(`Uploaded ${result.displayName} (${result.size} bytes).`);
    } catch (error) {
      setUploadMessage(error instanceof Error ? error.message : "Upload failed.");
      // TODO FE-11: distinguish validation/provider/network errors and offer explicit cancel/retry.
    }
  }

  return <>
    <section aria-labelledby="realtime-heading">
      <h2 id="realtime-heading">FE-16 · Realtime job updates</h2>
      <p role="status" aria-live="polite">Connection: {connection}</p>
      <p className="muted">Disconnected clients must refetch durable status over HTTP; events are not the source of truth.</p>
      {events.length === 0 ? <p>No job events received.</p> : <ul>{events.map((event, index) => (
        <li key={`${event.jobId}-${event.occurredAt}-${index}`}>Job {event.jobId}: {event.status} ({event.completedItems})</li>
      ))}</ul>}
    </section>
    <section aria-labelledby="upload-heading">
      <h2 id="upload-heading">FE-11 · Signed upload states</h2>
      <p className="muted">Harmless files only; Java must validate and issue upload parameters. No provider secret is used here.</p>
      <label>Choose file (max 5 MiB)
        <input type="file" onChange={onFile} />
      </label>
      <progress max={100} value={progress} aria-label="Upload progress" />
      <p role="status" aria-live="polite">{uploadMessage}</p>
      {/* TODO: fetch signed parameters from INT-03, upload to the provider, then verify the result on Java. */}
    </section>
  </>;
}
