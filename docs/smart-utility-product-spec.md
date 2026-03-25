# SMART UTILITY — Full Product Team Output

## Phase 1 — Market Research
The utility app category remains durable on Android because core jobs-to-be-done are recurring: scanning, converting, organizing, and sharing. The strongest opportunity is execution quality rather than category invention.

### Category signals
- **Strong performers:** Adobe Scan, CamScanner, Microsoft Lens (document workflows), Google Lens (general visual search), iLovePDF/Smallpdf mobile (PDF actions).
- **Fragmented segments:** unit converters, niche calculators.
- **High daily usage + weak monetization:** currency conversion tools.

### Oversaturated categories
- Standalone QR scanners (now mostly replaced by native camera support).
- Basic calculator replacements.
- Generic weather apps and plain notes apps.

### Competitive gap
Most all-in-one utility apps feel cluttered, ad-heavy, and visually outdated. Scanner-first apps are capable, but often impose intrusive paywalls or slow startup.

### Opportunity to win
A focused multi-tool app can win if it delivers:
1. Open-to-action in under 2 seconds.
2. Best-in-class document edge detection + export quality.
3. A compact set of high-frequency tools.
4. Modern, calm UX and fair monetization.

---

## Phase 2 — Product Positioning
### Positioning statement
**Smart Utility** is the premium-feeling, everyday utility toolkit for people who need scanning and conversion done fast, reliably, and without app clutter.

### Value proposition
- One app for the 6–8 core tasks users repeat weekly.
- Faster launch and faster first action than incumbents.
- Better scanner output quality in the free tier.
- Freemium model that rewards use instead of interrupting it.

### Brand promise
"Useful in seconds. Trusted for years."

### Product principles
- Speed over spectacle.
- Clarity over feature overload.
- Quality over quantity.
- Trust over growth hacks.

---

## Phase 3 — App Strategy
### Strategic choice: depth over breadth
Ship only high-frequency tools and execute each one to a premium standard.

### V1 tool set (12 tools)
1. Document scanner
2. OCR text extraction
3. PDF merge
4. PDF split
5. Image to PDF
6. QR scan
7. Currency converter
8. Unit converter (length, weight, temp, speed, volume)
9. Percentage + tax calculator
10. Tip/bill split calculator
11. Data converter (KB/MB/GB)
12. Time zone converter

### Onboarding strategy
- No forced sign-up.
- One permission explanation screen (camera).
- Two value-focused highlights.
- First scan within 30 seconds of install.

### Retention levers
- Searchable history for scans/conversions.
- Favorites and pinned tools.
- Smart recents on home screen.

---

## Phase 4 — Feature Architecture
### Core modules
- **Scan Core:** camera capture, edge detection, perspective correction, filters, export.
- **OCR Core:** on-device text recognition, copy/share/save.
- **File Tools Core:** PDF create/merge/split/compress.
- **Convert Core:** currency + unit + calculator tools.
- **History Core:** searchable timeline + favorites + re-run action.
- **Premium Core:** entitlements, paywall, restore purchases.

### Feature tiers
- **Free:** scanner, OCR, QR, key converters, basic exports.
- **Pro:** batch scan, high-res export, PDF compression presets, watermark removal, advanced converters, cloud backup.

### Guardrails
- Every feature must satisfy at least one persona’s weekly use case.
- Any feature with low 30-day usage share is a deprecation candidate.

---

## Phase 5 — User Experience Design
### Primary personas
1. **Office Worker (28–42):** scans receipts/business cards; wants clean PDF and speed.
2. **Student (18–25):** daily OCR + unit conversion; values retrieval from history.
3. **Traveler (25–45):** currency + QR in bursts; needs offline confidence.

### Navigation model
Bottom nav with four tabs:
- Home
- Scan (direct camera action)
- Convert
- History

### UX standards
- One-handed reach for primary actions.
- ≤2 taps from home to top tools.
- Empty states with guidance, not blank surfaces.
- Minimal interruptions before first value.

---

## Phase 6 — Design System
### Visual identity
- **Primary color:** Indigo `#4F46E5`
- **Base surfaces:** White + Light Gray
- **Text:** Near-black neutrals

### Design character
Premium, calm, tool-first. Not playful; not enterprise-heavy.

### Core tokens
- 8pt spacing scale.
- Rounded corners: 12–16dp for cards; 20dp for CTA pills.
- Strong hierarchy: semibold headings, high-contrast body text.

### Motion
- Fast, meaningful transitions (~250ms) for task continuity.
- Avoid decorative animation that slows core workflows.

---

## Phase 7 — Screen-by-Screen Blueprint
### Home
- Personalized greeting + quick actions.
- Top tools row and recents list.
- Cards: Scan, OCR, PDF tools, Convert, Favorites.

### Scan flow
1. Camera opens instantly.
2. Auto-edge suggestion overlay.
3. Manual adjust (optional).
4. Filter + crop.
5. Export as PDF/JPG.

### Convert hub
- Segmented categories for units/currency/calculators.
- Last-used converter pinned at top.
- Swap values in one tap.

### History
- Unified timeline of scans + conversions.
- Filters by type.
- Re-run, share, duplicate, or favorite actions.

### Premium paywall
- Clear feature comparison.
- Monthly, annual, lifetime options.
- Restore purchases visible and easy.

---

## Phase 8 — Technical Architecture
### Stack
- **Platform:** Android (Kotlin)
- **UI:** Jetpack Compose
- **Architecture:** MVVM + Clean boundaries
- **DI:** Hilt
- **Storage:** Room + DataStore
- **Imaging:** CameraX + ML Kit
- **Billing:** Google Play BillingClient

### State and events
- `UiState` as sealed classes via `StateFlow`.
- UI observes with `collectAsStateWithLifecycle()`.
- One-off effects through `Channel<UiEvent>`.

### Scanner implementation
- `ImageAnalysis` for live QR processing.
- `ImageCapture` for document capture.
- Edge detection + perspective correction for flat output.
- On-device OCR via ML Kit `InputImage.fromBitmap()`.

### Premium handling
- `PremiumRepository` wraps BillingClient.
- `isPro(): Flow<Boolean>` exposed to domain layer.
- Entitlement cache in DataStore to reduce launch latency.

---

## Phase 9 — Development Execution Plan
### Sprint 1 (Weeks 1–2): Foundations
- App shell, navigation, design system tokens.
- Camera integration baseline.
- Analytics and crash reporting setup.

### Sprint 2 (Weeks 3–4): Scanner MVP
- Edge detection, crop, filters.
- PDF export + share.
- Initial history logging.

### Sprint 3 (Weeks 5–6): Converters + OCR
- OCR extraction flow.
- Currency and unit converter modules.
- Favorites and recent tools.

### Sprint 4 (Weeks 7–8): Monetization + QA
- Billing integration.
- Free/pro gating and paywall copy.
- Performance tuning and regression testing.

### Launch readiness checklist
- Cold start under 2s on target devices.
- Scanner output quality benchmarked against top 3 competitors.
- Crash-free sessions target: 99.5%+.

---

## Phase 10 — Final Output Summary
### Key strategic bets
1. **Scanner quality is the growth engine.** Ratings and referrals will correlate with output quality.
2. **Fair monetization wins trust.** Lifetime purchase supports anti-subscription users.
3. **Usage-driven roadmap.** Prioritize V2 by top actions in history events.

### Proposed pricing
- Free tier with meaningful utility.
- Pro Monthly: $4.99
- Pro Annual: $19.99
- Pro Lifetime: $29.99

### V2 signal framework
Track and rank by:
- Feature activation rate
- Weekly repeat rate
- Export/share completion rate
- Pro conversion by feature exposure

### Success definition (first 90 days)
- 4.6+ Play Store rating.
- 25%+ D30 retention for scanner users.
- 2–4% free-to-paid conversion.
- Top 3 tools account for >70% weekly activity (focus indicator).

---

## Closing Team Notes
- Invest earliest engineering effort in document capture quality and export fidelity.
- Keep onboarding friction minimal; value should appear before monetization prompts.
- Build fewer tools, but make them the best-in-class options users trust daily.
