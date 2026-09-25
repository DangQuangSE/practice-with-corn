# Phase 06 — Docker, CI, and local operations

**IDs:** OPS-01–OPS-14. **Runtime:** Docker Compose v2, Java 21, Node.js 24, PostgreSQL 18.6. Local only; no production access or credential is required.

This lab complements—not replaces—the preserved source examples [`dockerFile`](../../docker/dockerFile) and [`dockerCompose`](../../docker/dockerCompose). The Java and Next.js Dockerfiles are runnable examples. Compose's default profile starts the API; `support` adds PostgreSQL, Redis, and Mailpit.

## Start the lab

From this directory:

```powershell
Copy-Item .env.example .env
# Set unique local-only passwords before starting the support/object-store profiles.
docker compose config
docker compose up --build -d
docker compose ps
Invoke-WebRequest http://localhost:8081/actuator/health
docker compose --profile support up -d
docker compose --profile support ps
```

The API health endpoint should return `{"status":"UP"}`. Support ports are bound to loopback only. On first database creation Compose loads the canonical schema and deterministic seed; a `postgres-readiness-check` one-shot service waits for the database health check. Set a non-empty local-only `POSTGRES_PASSWORD` before starting `support`, and `MINIO_ROOT_PASSWORD` before starting `object-store`. `docker compose --profile object-store up -d` starts the optional S3-compatible local object service. `docker compose down` preserves named volumes. Resetting any state is a separate, destructive exercise: inspect the resolved Compose project and volume first; only then use the documented `docker compose down --volumes` against this lab.

To build the Next.js production image independently, run `docker build -t practice-next-foundations .` from `nextjs-business-rules/exercises/phase-02-next-foundations`; run the container with `-p 3000:3000` and verify the page.

## Lab map

| IDs | Practice prompt / expected observation |
|---|---|
| OPS-01 | Copy `.env.example` to ignored `.env`; compare Spring profiles and container env; verify no credential enters source or client bundle. |
| OPS-02 | Inspect the Java multi-stage Dockerfile; final image contains JRE/app only, runs non-root, and places JVM flags before `-jar`. |
| OPS-03–04 | Inspect Next standalone output, lockfile-first dependency layer, `.dockerignore`, cache invalidation, and image build context. |
| OPS-05 | Change only host ports; container-to-container traffic uses service DNS, while published support ports remain loopback-only. |
| OPS-06 | Stop/start and confirm PostgreSQL persistence. Learn the separate volume-reset command and data-loss consequence before running it. |
| OPS-07 | Compare `service_started` with health checks; use `pg_isready` and the API actuator health endpoint. |
| OPS-08 | Extend the lab with an idempotent migration job that waits for DB health; verify restart behavior and migration ownership. |
| OPS-09 | Exercise PostgreSQL, Redis, Mailpit, and the optional S3-compatible object-store profile; keep credentials local and provider adapters replaceable. |
| OPS-10 | Adapt `nginx.conf.example`; route `/api/` to Java and `/` to Next.js on a private Compose network. |
| OPS-11–12 | Review [CI workflow](../../../.github/workflows/practice-ci.yml): pinned runtime/action majors, lockfile installs, Maven/Next builds, and dependency cache keys. Extend it with tests when you choose to practice tests; this workflow intentionally skips tests per your instruction. |
| OPS-13 | Use the diagnosis checklist below for unhealthy services, port conflicts, DNS/network errors, bad env, and log correlation. |
| OPS-14 | Optional: draft a local release profile and rollback checklist; never point this lab at production. |

## Diagnose before changing state

```powershell
docker compose ps
docker compose logs --tail 100 api postgres redis mailpit
docker inspect --format '{{json .State.Health}}' <container-name>
docker network ls
docker compose config
```

Check the service name/port from inside a container versus the published host port; confirm health before debugging application-level connection errors. Do not print `.env` or paste secrets into logs.

## Preserved-source review prompts

The older `dockerFile` / `dockerCompose` drafts intentionally contain review material: nonstandard filenames, a Compose Dockerfile/context mismatch, a fixed demo password, `depends_on` without readiness, and Java `-jar` option ordering. Identify the impact and propose a minimal correction before comparing with the new examples. Do not overwrite the originals.

## References

- [Docker multi-stage builds](https://docs.docker.com/build/building/multi-stage/)
- [Compose startup order and health conditions](https://docs.docker.com/compose/how-tos/startup-order/)
- [PostgreSQL official image volume changes for version 18](https://github.com/docker-library/docs/blob/master/postgres/README.md)
- [GitHub Actions Java/Maven build](https://docs.github.com/en/actions/tutorials/build-and-test-code/java-with-maven)
- [Mailpit releases](https://github.com/axllent/mailpit/releases)
