package org.skypro.counter.model.service;

import org.skypro.counter.model.basket.BasketItem;
import org.skypro.counter.model.basket.ProductBasket;
import org.skypro.counter.model.basket.UserBasket;
import org.skypro.counter.model.exception.NoSuchProductException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket basket;
    private final StorageService storageService;

    public BasketService(ProductBasket basket, StorageService storageService) {
        this.basket = basket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        if (storageService.getProductById(id).isEmpty()) {
            throw new NoSuchProductException();
        }
        basket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> items = basket.getProductsInBasket()
                .entrySet()
                .stream()
                .map(p -> new BasketItem(storageService.getProductById(p.getKey())
                        .orElseThrow(NoSuchProductException::new), p.getValue()))
                .toList();
        return new UserBasket(items);
    }
}
