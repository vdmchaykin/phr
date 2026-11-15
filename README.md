# Personal Health Record (PHR)
PHR is a self-hosted personal health record: a set of Java/Spring Boot microservices that store and organize my own medical history: doctor visits, diagnoses, medications, lab results, vaccinations, scanned documents and medical invoices, and remind me of what is due.

**Why.** In Germany, medical information is scattered across paper Arztbriefe, lab printouts, the yellow vaccination booklet and different practice systems. With private health insurance (PKV) a stack of doctors' invoices arrives on top, each to be paid and then submitted for reimbursement. PHR brings all of it into one searchable place that I actually use day to day, through a Telegram bot for quick capture and a web interface for reviewing and charts.

**Who uses it.** Me and my partner, each with an own account; one person can grant the other access to selected data, for example only invoices. Real data stays on my own server in Germany; the public repository ships with a demo profile and generated sample patients.

**What it demonstrates.** Production-style microservice development with Java 25 and Spring Boot 4: REST APIs, event-driven communication with Kafka, OAuth2 with Keycloak, the HL7 FHIR data standard used in German digital health (ePA, E-Rezept), observability, automated tests and a documented development process in Jira.


## Definition of Done

A work item is **Done** when:

- [ ] Acceptance criteria of the Jira ticket are met
- [ ] Code is merged into `main` via a pull request; ticket key (`PHR-XX`) in branch name, commits and PR title
- [ ] New or changed logic is covered by tests; all tests pass in CI
- [ ] Self-review of the diff done, PR template checklist completed
- [ ] No secrets and no real personal or health data in the repository
- [ ] API changes reflected in OpenAPI docs; README or ADR updated if behaviour or architecture changed
- [ ] Learning log entry written
- [ ] Jira ticket moved to Done

**Spikes** (learning tasks) are done when the findings are written down in `docs/learning-log.md`.
