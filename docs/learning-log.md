# Learning Log

## 2026-11-15 – Etappe 0
- Wiederholt: Datentypen, Operatoren, Schleifen, Methoden, Arrays
- Jira-Board und Backlog (10 Epics, 62 Stories) eingerichtet

## 2026-11-16 – Etappe 0

**Erledigt**
- Interfaces, Polymorphismus, Dependency Injection (Vererbung und abstrakte Klassen kurz)
- Moderne Java-Features: `record`, `enum`, `sealed`, Pattern Matching, `var`, Text Blocks
- `java.time`: `LocalDate`, `LocalDateTime`, `Period`, `Duration`, Formatierung
- Datenmodell für records-service und billing-service entworfen (`docs/data-model.dbml`)

**Erkenntnisse**
- Spring arbeitet über Interfaces: Repositories sind Interfaces, Abhängigkeiten werden über den Konstruktor injiziert
- `record` eignet sich für DTOs (unveränderlich, `equals`/`hashCode` automatisch)
- `enum` für Status-Felder; in JPA mit `@Enumerated(EnumType.STRING)`, nicht als Ordinalzahl speichern
- `LocalDate` für Datumswerte ohne Uhrzeit (Besuche, Fälligkeiten), `LocalDateTime`/`Instant` für Zeitstempel
- Abgeleitete Werte (z. B. „überfällig“) nicht speichern, sondern berechnen
- Geldbeträge als `numeric` in der DB und `BigDecimal` in Java, nie `double`
