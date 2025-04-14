package org.skypro.counter.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final double price;
    private final UUID id;

    public SimpleProduct(UUID id, String name, double price) {
        super(id, name);
        if(price <= 0) {
            throw new IllegalArgumentException("Цена продукта должна быть больше нуля");
        }
        this.price = price;
        this.id = id;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return getName() + ": " + price;
    }

    @Override
    public UUID getId() {
        return id;
    }
}
