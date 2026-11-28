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

## 2026-11-27 – Etappe 0

**Erledigt**
- Collections: `List`, `Set`, `Map`, `equals` & `hashCode`, `Comparator`
- Generics (Grundlagen): `List<Visit>`, `Optional<T>`, `JpaRepository<Visit, Long>` sicher lesen

**Erkenntnisse**
- `ArrayList` für geordnete Listen, `HashSet` gegen Duplikate, `HashMap` für Zuordnung Schlüssel → Wert
- `equals` und `hashCode` immer zusammen überschreiben – sonst funktionieren `HashSet`/`HashMap` nicht korrekt
- Bei JPA-Entities vorsichtig mit `equals`/`hashCode`: die ID ist vor dem Speichern noch `null`
- `Comparator.comparing(...).thenComparing(...)` statt eigener `compareTo`-Logik, z. B. Laborwerte nach Datum sortieren
- Generics sorgen für Typsicherheit zur Compile-Zeit: `JpaRepository<Visit, Long>` = Entity-Typ + ID-Typ

## 2026-11-28 – Etappe 0

**Erledigt**
- Lambdas, funktionale Interfaces, Streams, Optional
- Annotationen, Maven, JUnit 5