# Smart Scanner / Converter / Tool App — Implementation Plan

## Phase 1 — Project Architecture

### Module strategy
- `:app` (composition root + feature delivery for v1)
- Future split-ready boundaries by package:
  - `core/*` for reusable platform/business primitives
  - `feature/*` for vertical slices

### Package structure
- `app/` application entry, DI modules, top-level activity
- `core/navigation` app route contracts + nav graph
- `core/designsystem` shared Compose components
- `core/domain` use cases + repository contracts + domain models
- `core/data` Room entities/dao/repository implementations
- `core/datastore` app preferences persistence
- `feature/<name>` UI + state + ViewModel per feature
- `ui/theme` app design tokens and Material theme

### Layers and responsibilities
- UI: Compose screens, state rendering, event forwarding
- Presentation: ViewModels, state reducers, one-shot actions
- Domain: use cases encapsulating business logic
- Data: local/remote data source orchestration
- Platform: camera, OCR, background work abstractions

### Naming conventions
- Screen: `FeatureScreen`
- ViewModel: `FeatureViewModel`
- UI state: `FeatureUiState`
- Use case: Verb + Noun + `UseCase`
- Repository interface: `SomethingRepository`
- Room entity: `SomethingEntity`

### State management
- `StateFlow` for persistent screen state
- Event-intent functions on ViewModel
- Immutable data classes for state

### Navigation architecture
- Single `NavHost` with sealed destination routes
- Feature screens own only route entrypoints; no cross-feature coupling

### DI structure
- Hilt `SingletonComponent` modules for database + repositories
- Constructor injection in ViewModels/use cases/repositories

## Phase 2 — Feature Breakdown

### Onboarding
- UI: intro + CTA
- ViewModel: completion tracking
- Domain: mark onboarding complete
- Data source: DataStore flag

### Home dashboard
- UI: cards to all tools
- ViewModel: load dashboard stats (future)
- Domain: aggregate quick actions

### Scanner + OCR
- UI: camera preview + recognized text editor
- ViewModel: scanning state + save action
- Domain: save scan use case
- Data source: CameraX pipeline + ML Kit recognizer + Room save

### Converters
- UI: converter inputs + outputs
- ViewModel: conversion state
- Domain: conversion calculators (currency/unit/text)

### Quick tools
- UI: shortcuts from dashboard cards
- Domain: command-style tool actions

### History
- UI: list of saved scans/conversions
- ViewModel: observe history flow
- Domain: observe/delete history
- Data source: Room

### Premium
- UI: paywall and plans
- ViewModel: entitlement state
- Domain: subscribe/restore purchases
- Data source: BillingClient (future wiring)

### Settings
- UI: preferences toggles
- ViewModel: observe/update prefs
- Domain: preference use cases
- Data source: DataStore

## Phase 3 — Development Order
1. Foundation: Gradle + Hilt + Compose theme + navigation shell
2. Core data: Room + DataStore + repository contracts
3. Home/dashboard and reusable design system components
4. Scanner skeleton + OCR pipeline integration seam
5. Converter feature
6. History feature linked to scanner saves
7. Premium/paywall surface (billing integration seam)
8. Settings and app-level behavior wiring
9. WorkManager jobs for cleanup/sync (when backend exists)

## Phase 4/5 — Code generation strategy
Implemented in this branch as production-ready scaffold with scalable boundaries and feature slices. Subsequent iterations can replace stubs with real CameraX analyzer, ML Kit processor, Billing flows, and WorkManager jobs.
