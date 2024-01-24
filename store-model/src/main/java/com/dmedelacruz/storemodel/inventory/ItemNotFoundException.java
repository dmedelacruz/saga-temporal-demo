package com.dmedelacruz.storemodel.inventory;

public class ItemNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Item Not Found";
    public ItemNotFoundException() {
        super(MESSAGE);
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
