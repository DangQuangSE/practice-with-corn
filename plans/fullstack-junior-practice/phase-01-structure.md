# Phase 01 — Curriculum structure and authoring rules

**Stories:** P1 broad inventory; P1 focused standalone/paired learning; P1 docs theory/index; P3 capstone explicitly out of scope.
**Dependencies:** None.

## Deliverables

1. Confirm the inventory map and IDs as the authoring source of truth. Any changed/removed item must retain a short rationale and update traceability.
2. Define lightweight templates for a Java exercise, Next.js exercise, SQL challenge, DevOps lab, shared brief/API contract, and docs topic page. Every exercise template includes ID/title, tier, difficulty, objective, prerequisites, TODO/scaffold, acceptance criteria, verification command/expected result, and hint link/section.
3. Create/update track READMEs and `docs/fullstack-junior-practice/index.md`; link concepts to IDs. Do not place complete solutions in exercise files.
4. Define pairing conventions: shared brief/API contract, BE owner and FE owner sections, mock contract support, and independent checks. Define standalone marker where pairing adds no value.
5. Record framework/runtime versions per runnable module when implementation begins. Keep exercises focused and isolated; no growing shared application.

## Acceptance criteria

- Every row in [inventory-map.md](inventory-map.md) has one stable ID, phase, tier, difficulty, and shape.
- Templates can represent an exercise without assuming prior code from a different exercise.
- Docs navigation distinguishes Core from optional Extension and links to the actual modules.
- `DE_PRACTICE` is not created or added to the curriculum.

## Design Constraints

- Documentation and scaffolding only; do not implement business exercises in this phase.
- Preserve DSA and Java-interview material and pre-existing user changes.
- Do not create a monorepo product/capstone or force every competency into a paired task.

Preflight: Existing practice is organized as focused topic folders, with Vietnamese Markdown theory linking to independent source files; Java DSA uses one class per file and a local `Check.java`. The interview theory track is documentation-only, while existing DevOps files are drafts. Keep new exercises isolated by stable ID, keep shared briefs/contracts in docs, use no shared growing application, and preserve existing tracks and scripts.

## Quality and Testing State

- Quality: skipped by user; no `ck:quality` gate was run.
- Testing: not started; unit tests were skipped by user. Phase 01 is documentation/scaffolding only.
