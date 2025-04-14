package org.skypro.counter.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final double FIX_PRICE = 100.0;
    private final UUID id;

    public FixPriceProduct(UUID id, String name) {
        super(id, name);
        this.id = id;
    }

    @Override
    public double getPrice() {
        return FIX_PRICE;
    }

    @Override
    public String toString() {
        return getName() + ": Фиксированная цена " + FIX_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public UUID getId() {
        return id;
    }
}
