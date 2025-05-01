package org.skypro.counter.model.basket;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> items;
    private final int totalCost;


    public UserBasket(List<BasketItem> items) {
        this.items = items;
        this.totalCost = items.stream().mapToInt(i -> (int) (i.getProduct().getPrice() * i.getQuantity())).sum();
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public int getTotalCost() {
        return totalCost;
    }
}
