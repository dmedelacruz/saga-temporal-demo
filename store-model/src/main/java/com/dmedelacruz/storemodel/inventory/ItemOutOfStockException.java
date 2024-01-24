package com.dmedelacruz.storemodel.inventory;

public class ItemOutOfStockException extends RuntimeException {
    private static final String MESSAGE = "Item is out of stock";

    public ItemOutOfStockException() {
        super(MESSAGE);
    }

    public ItemOutOfStockException(String message) {
        super(message);
    }
}
