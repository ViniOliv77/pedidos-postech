package com.fiap.tech.pedidos_postech.domain.order.enums;

public enum Status {
    REQUESTED,
    APPROVED,
    IN_PROGRESS,
    WAITING_DRIVER,
    COLLECTED,
    IN_TRANSPORT,
    DELIVERED,
    FINISHED,
    CANCELLED;

    public static Boolean isCancellable(Status status) {
        return switch (status) {
            case REQUESTED, APPROVED, IN_PROGRESS, WAITING_DRIVER -> true;
            default -> false;
        };
    }
}
