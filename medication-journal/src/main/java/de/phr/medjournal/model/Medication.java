package de.phr.medjournal.model;

import java.time.LocalDate;

public record Medication(String name, String dosage, LocalDate start, LocalDate end) {
    public Medication {
        name = requireText(name, "Name");
        dosage = requireText(dosage, "Dosage");

        if (start == null) {
            throw new IllegalArgumentException("Start date is required");
        }
        if (end != null && end.isBefore(start)) {
            throw new IllegalArgumentException("End date is before start date");
        }
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("%s must not be blank".formatted(fieldName));
        }
        if (value.contains(";")) {
            throw new IllegalArgumentException("%s must not contain ';'".formatted(fieldName));
        }
        return value.strip();
    }

    public static Medication ongoing(String name, String dosage, LocalDate start) {
        return new Medication(name, dosage, start, null);
    }

    public boolean isActiveOn(LocalDate day) {
        return !day.isBefore(start) && (end == null || !day.isAfter(end));
    }

    public boolean isOngoing() {
        return end == null;
    }

    public Medication withEnd(LocalDate newEnd) {
        return new Medication(name, dosage, start, newEnd);
    }
}
