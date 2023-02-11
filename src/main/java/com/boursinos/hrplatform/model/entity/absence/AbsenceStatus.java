package com.boursinos.hrplatform.model.entity.absence;

public enum AbsenceStatus {

    REQUESTED("requested"),
    APPROVED("approved"),
    REJECTED("rejected");

    private final String value;

    AbsenceStatus(final String value) {
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
