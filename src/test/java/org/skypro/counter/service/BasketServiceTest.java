package org.skypro.counter.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.counter.model.basket.BasketItem;
import org.skypro.counter.model.basket.ProductBasket;
import org.skypro.counter.model.basket.UserBasket;
import org.skypro.counter.model.exception.NoSuchProductException;
import org.skypro.counter.model.product.Product;
import org.skypro.counter.model.product.SimpleProduct;
import org.skypro.counter.model.service.BasketService;
import org.skypro.counter.model.service.StorageService;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket basket;
    @Mock
    private StorageService storageService;
    @InjectMocks
    private BasketService basketService;

    @Test
    void addProduct_nonExistentProduct_throwsException() {
        UUID id = UUID.randomUUID();
        doThrow(new NoSuchProductException()).when(storageService).getProductById(id);
        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(id));
        verify(storageService).getProductById(id);
    }

    @Test
    void addProduct_existentProduct_callsAddProductOnProductBasket() {
        UUID id = UUID.randomUUID();
        Product apple = new SimpleProduct(UUID.randomUUID(), "яблоко", 65);
        when(storageService.getProductById(id)).thenReturn(Optional.of(apple));
        basketService.addProduct(id);
        verify(basket).addProduct(id);
    }

    @Test
    void getUserBasket_productBasketIsEmpty_returnsEmptyBasket() {
        UserBasket userBasket = basketService.getUserBasket();
        assertEquals(0, userBasket.getItems().size());
    }

    @Test
    void getUserBasket_productBasketIsFull_returnsCorrectBasket() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        Product orange = new SimpleProduct(id1, "апельсин", 75);
        Product lemon = new SimpleProduct(id2, "лимон", 110);
        Product banana = new SimpleProduct(id3, "банан", 90);
        when(storageService.getProductById(id1)).thenReturn(Optional.of(orange));
        when(storageService.getProductById(id2)).thenReturn(Optional.of(lemon));
        when(storageService.getProductById(id3)).thenReturn(Optional.of(banana));
        Map<UUID, Integer> basketMap = new HashMap<>();
        basketMap.put(id1, 1);
        basketMap.put(id2, 2);
        basketMap.put(id3, 3);
        when(basket.getProductsInBasket()).thenReturn(basketMap);
        UserBasket correctBasket = basketService.getUserBasket();
        Map<Product, Integer> expectedMap = new HashMap<>();
        expectedMap.put(orange, 1);
        expectedMap.put(lemon, 2);
        expectedMap.put(banana, 3);
        Map<Product, Integer> actualMap = correctBasket.getItems()
                .stream().collect(Collectors.toMap(BasketItem::getProduct, BasketItem::getQuantity));
        assertEquals(expectedMap, actualMap);
    }



}
