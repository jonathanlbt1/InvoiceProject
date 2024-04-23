package com.challenge.invoice.model;

public enum Status {
    PAGO("PAGO"),
    VENCIDO("VENCIDO"),
    PENDENTE("PENDENTE");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
