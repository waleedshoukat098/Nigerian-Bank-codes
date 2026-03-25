# Smart Scanner / Converter / Tool App — Release Candidate Package

## 1) What is fully implemented in this pass
- Billing-ready premium architecture with `PremiumManager` abstraction and `PlayBillingManager` implementation.
- Entitlement source-of-truth flow combining Play Billing state + local cached fallback.
- Purchase flow entry points for monthly/yearly plans and restore purchase handling.
- Premium gating helper (`PremiumGate`) integrated into converter lock UX.
- Premium UI now reads billing catalog pricing when available.
- Stability hardening: safer camera unbind, scanner and converter feedback channels, and improved edge handling from prior pass.

## 2) What is prepared but still requires Play Console setup
- Play Console product IDs must exist and match:
  - `smart_scanner_premium_monthly`
  - `smart_scanner_premium_yearly`
- Subscription offers/base plans must be configured.
- App signing/upload track and test account setup required for billing validation.
- Backend receipt validation is recommended before production subscription launch.

## 3) Practical test strategy
### Priority 1 (must-have)
- `ConverterViewModelTest`
  - invalid input -> error message
  - premium-locked tools blocked for free users
  - save emits event only when result exists
- `ScannerViewModelTest`
  - save QR/OCR emits success event
  - blank OCR emits guardrail feedback
- `HistoryViewModelTest`
  - filter/search behavior
  - delete and clear events

### Priority 2
- `PremiumViewModelTest`
  - catalog + entitlement mapping
  - restore triggers feedback
- Repository tests for `ScanRepositoryImpl` (Room in-memory)

### Priority 3
- Compose UI tests:
  - converter lock chip label
  - scanner permission prompt rendering
  - history clear dialog visibility

## 4) Recommended test file structure
- `app/src/test/java/.../feature/scanner/ScannerViewModelTest.kt`
- `app/src/test/java/.../feature/converter/ConverterViewModelTest.kt`
- `app/src/test/java/.../feature/history/HistoryViewModelTest.kt`
- `app/src/test/java/.../feature/premium/PremiumViewModelTest.kt`
- `app/src/androidTest/java/.../feature/converter/ConverterScreenTest.kt`
- `app/src/androidTest/java/.../feature/scanner/ScannerScreenTest.kt`

## 5) Play Store launch assets
### App naming options (10)
1. Smart Scanner Toolkit
2. Smart Scan & Convert
3. ScanFlow Utility
4. Smart QR OCR Converter
5. ScanMate Utility Hub
6. ScanVault Tools
7. Scan & Convert Pro
8. Smart Utility Scanner
9. QuickScan Converter
10. OCR & QR Smart Tools

### Tagline options (10)
1. Scan, extract, convert — instantly.
2. Your all-in-one smart utility scanner.
3. QR, OCR, and converters in one calm app.
4. Turn scans into actions.
5. Fast scanning. Clean results. Useful tools.
6. Productivity tools, built for everyday tasks.
7. Capture text. Convert values. Stay organized.
8. Smart scanning with practical converters.
9. The utility app for modern workflows.
10. Scan smarter, work faster.

### Short description options
- Scan QR, extract text with OCR, and convert values in one simple app.
- All-in-one scanner and converter with history, privacy-first storage, and premium tools.
- Fast QR + OCR + smart converters with clean, searchable history.

### Full Play Store description
Smart Scanner / Converter / Tool App helps you move faster with everyday tasks. Scan QR and barcodes, extract text from images with OCR, and run practical conversions — all in one clean workspace.

Whether you’re scanning links, pulling text from receipts, or calculating values quickly, Smart Scanner keeps everything searchable and organized.

## What you can do
- Scan QR and barcodes with real-time camera detection.
- Extract editable text from photos and documents.
- Use converter tools for unit, currency, percentage, age, and storage calculations.
- Save and search your history with filters.
- Share results instantly.

## Why users choose Smart Scanner
- Clean and calm interface with fast actions.
- Local-first behavior for better privacy.
- Built-in premium options for power users.
- Designed for speed, clarity, and reliability.

Upgrade to Premium to unlock unlimited scanning and advanced tools.

### Key feature bullets
- Real-time QR & barcode scanning
- OCR text extraction from images
- Smart converter suite
- Searchable history with filters
- Share, copy, and quick actions
- Premium plan for power users

### ASO keyword strategy
- Primary keywords: qr scanner, barcode scanner, ocr scanner, text scanner, unit converter
- Secondary: currency converter, age calculator, storage converter, scan and save
- Positioning: “all-in-one scanner + utility converter”
- Title keyword mix suggestion: “Smart Scanner: QR OCR Converter”

### Onboarding copy polish
- Headline: “Scan smarter. Convert faster.”
- Body: “Use QR scanning, OCR extraction, and practical converters in one private, organized app.”
- Trust line: “Your results are stored locally unless you choose to share.”

### Premium copy polish
- Headline: “Unlock Pro Productivity”
- Subhead: “Unlimited scans, advanced converters, and faster workflows.”
- CTA: “Upgrade to Premium” / “Restore Purchases”

### Settings / permission / trust copy
- Camera permission: “Camera is used only to scan codes on-device.”
- Privacy note: “Data stays on your device unless you export it.”
- History note: “You can clear saved items anytime.”

### App icon direction
- Minimal rounded-square icon with a scan frame + lightning bolt/check mark.
- Blue primary gradient with white glyph for trust + utility feel.

### Screenshot content plan (6)
1. “Scan QR Codes Instantly” — camera preview + result actions
2. “Extract Text from Images” — OCR editor view
3. “All-in-One Converter Suite” — converter chips + result card
4. “Searchable History” — filters + recent records
5. “Premium Tools for Power Users” — plan cards + comparison
6. “Privacy-First Settings” — trust and controls

### Privacy policy checklist
- Camera usage purpose
- OCR processing disclosure (on-device or service-based)
- Data storage location and retention
- Sharing/export behavior
- Subscription and billing terms
- Contact/support channel
- Deletion/clear-history controls

### Play Store release checklist
- [ ] Final app name and package consistency
- [ ] Subscription products live in Play Console
- [ ] Internal testing with licensed test users
- [ ] Crash reporting integrated
- [ ] Privacy policy URL published
- [ ] Store listing assets finalized
- [ ] Content rating + data safety form completed
- [ ] Pre-launch report passed
- [ ] Release notes prepared

## 6) Final release risk report
### A. Remaining release blockers
- Play Billing requires real Play Console products and end-to-end validation.
- No automated test suite merged yet.

### B. Improvements implemented in this pass
- Billing architecture added with entitlement and restore hooks.
- Premium gating standardized for converter tools.
- RC documentation and launch package drafted.

### C. Remaining known weaknesses
- Billing backend validation not present.
- No analytics funnel instrumentation.
- No screenshot/icon assets committed yet.

### D. Must-do before shipping
- Enable real subscriptions in Play Console and verify purchase flow.
- Add unit tests for ViewModels and critical flows.
- Run full QA on API 26/29/34 with dark mode and rotation.

### E. Recommended next milestone after release
- v1.1: cloud backup + cross-device sync + PDF export.
