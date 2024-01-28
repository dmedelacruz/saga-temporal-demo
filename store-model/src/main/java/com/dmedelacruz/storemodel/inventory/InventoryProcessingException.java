package com.dmedelacruz.storemodel.inventory;

public class InventoryProcessingException extends RuntimeException {

    private static final String MESSAGE = "Error Processing Inventory Request";
    public InventoryProcessingException() {
        super(MESSAGE);
    }

    public InventoryProcessingException(String message) {
        super(message);
    }

}
