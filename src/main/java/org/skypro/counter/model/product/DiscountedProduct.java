package org.skypro.counter.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final double basePrice;
    private final int discount;
    private final UUID id;

    public DiscountedProduct(UUID id, String name, double basePrice, int discount) {
        super(id, name);
        if(basePrice < 0) {
            throw new IllegalArgumentException("Цена продукта должна быть больше нуля");
        }
        if(discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка на продукт должна быть числом в диапазоне от 0 до 100 включительно");
        }
        this.basePrice = basePrice;
        this.discount = discount;
        this.id = id;
    }

    @Override
    public double getPrice() {
        return basePrice * (1 - discount / 100.0);
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + discount + "%)";
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
