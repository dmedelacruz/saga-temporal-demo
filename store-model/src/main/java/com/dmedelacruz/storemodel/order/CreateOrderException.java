package com.dmedelacruz.storemodel.order;

public class CreateOrderException extends RuntimeException {

    private static final String MESSAGE = "Order Creation Failed";

    public CreateOrderException() {
        super(MESSAGE);
    }

    public CreateOrderException(String message) {
        super(message);
    }

}
