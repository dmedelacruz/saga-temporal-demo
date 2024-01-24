package com.dmedelacruz.storemodel.order;

public class OrderNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Order Not Found";

    public OrderNotFoundException() {
        super(MESSAGE);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}
