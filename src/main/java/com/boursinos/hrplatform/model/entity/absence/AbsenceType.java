package com.boursinos.hrplatform.model.entity.absence;

public enum AbsenceType {
    NORMAL("normal"),
    MATERNITY("maternity"),
    SICKNESS("sickness"),
    OTHER("other"),
    UNPAID("unpaid");

    private final String value;

    AbsenceType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
