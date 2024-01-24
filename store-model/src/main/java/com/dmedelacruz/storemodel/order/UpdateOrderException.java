package com.dmedelacruz.storemodel.order;

public class UpdateOrderException extends RuntimeException {

    private static final String MESSAGE = "Update Order Status Failed";

    public UpdateOrderException() {
        super(MESSAGE);
    }

    public UpdateOrderException(String message) {
        super(message);
    }


}
