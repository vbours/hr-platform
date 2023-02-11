package com.boursinos.hrplatform.model.entity.employee;

public enum ContractType {
    FULL_TIME("fullTime"),
    PART_TIME("partTime"),
    CONTRACTOR("CONTRACTOR");

    private final String value;

    ContractType(final String value) {
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
