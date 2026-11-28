package de.phr.medjournal.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MedicationTest {
    private static final LocalDate TODAY = LocalDate.of(2026, 11, 16);

    @Test
    void stripsName() {
        Medication m = new Medication("  Ibuprofen ", "400 mg", LocalDate.of(2026, 11, 10), null);
        assertEquals("Ibuprofen", m.name());
    }

    @Test
    void stripsDosage() {
        Medication m = new Medication("  Ibuprofen ", "   400 mg ", LocalDate.of(2026, 11, 10), null);
        assertEquals("400 mg", m.dosage());
    }

    @ParameterizedTest
    @CsvSource({
            "'', 400 mg, Name must not be blank",
            "Ibuprofen, '', Dosage must not be blank",
            "Ibuprofen;500, 500 mg, Name must not contain ';'",
    })
    void rejectsInvalidNameOrDosage(String name, String dosage, String expectedMessage) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                new Medication(name, dosage, LocalDate.of(2026, 10, 1), LocalDate.of(2026, 11, 1)));
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void rejectsMissingStartDate() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                new Medication("Ibuprofen", "400 mg", null, LocalDate.of(2026, 11, 1)));
        assertEquals("Start date is required", e.getMessage());
    }

    @Test
    void allowsEndOnStartDay() {
        Medication m = new Medication("Ibuprofen", "400 mg", LocalDate.of(2026, 10, 1), LocalDate.of(2026, 10, 1));
        assertEquals(LocalDate.of(2026, 10, 1), m.end());
    }

    @Test
    void rejectsEndBeforeStart() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->
                new Medication("Ibuprofen", "400 mg", LocalDate.of(2026, 10, 1), LocalDate.of(2026, 9, 30)));
        assertEquals("End date is before start date", e.getMessage());
    }

    @ParameterizedTest(name = "{0} -> active={1}")
    @CsvSource({
            "2026-11-01, true",
            "2026-11-28, true",
            "2026-12-01, true",
            "2026-12-02, false",
            "2026-10-31, false"
    })
    void isActiveFromStartUntilEndInclusive(LocalDate day, boolean expected) {
        Medication m = new Medication("Ibuprofen", "400 mg", LocalDate.of(2026, 11, 1), LocalDate.of(2026, 12, 1));
        assertEquals(expected, m.isActiveOn(day));
    }

    @Test
    void endedTodayIsActiveToday() {
        Medication m = new Medication("Ibuprofen", "400 mg", TODAY.minusDays(5), TODAY);
        assertTrue(m.isActiveOn(TODAY));
    }

    @Test
    void endedYesterdayIsNotActiveToday() {
        Medication m = new Medication("Ibuprofen", "400 mg", TODAY.minusDays(5), TODAY.minusDays(1));
        assertFalse(m.isActiveOn(TODAY));
    }

    @Test
    void ongoingMedicationIsActiveLongAfterStart() {
        Medication m = Medication.ongoing("Vitamin D", "1000 IE", TODAY.minusYears(1));
        assertTrue(m.isActiveOn(TODAY));
    }

    @Test
    void withEndReturnsCopyAndKeepsOriginal() {
        Medication m = new Medication("Ibuprofen", "400 mg", LocalDate.of(2026, 11, 1), LocalDate.of(2026, 12, 1));
        Medication m2 = m.withEnd(LocalDate.of(2027, 1, 1));

        assertEquals(LocalDate.of(2027, 1, 1), m2.end());
        assertEquals(LocalDate.of(2026, 12, 1), m.end());
        assertNotSame(m, m2);
    }

    @Test
    void withEndOnOngoingMedicationKeepsOriginalOngoing() {
        Medication m3 = Medication.ongoing("Ibuprofen", "400 mg", LocalDate.of(2025, 11, 28));
        Medication m4 = m3.withEnd(LocalDate.of(2027, 1, 1));

        assertEquals(LocalDate.of(2027, 1, 1), m4.end());
        assertNull(m3.end());
        assertNotSame(m3, m4);
    }

    @Test
    void isOngoingWithoutEndDate() {
        Medication m = Medication.ongoing("Vitamin D", "1000 IE", TODAY);
        assertTrue(m.isOngoing());
    }

    @Test
    void isNotOngoingWithEndDate() {
        Medication m = new Medication("Ibuprofen", "400 mg", TODAY, TODAY.plusDays(10));
        assertFalse(m.isOngoing());
    }
}
