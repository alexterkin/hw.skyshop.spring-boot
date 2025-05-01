package org.skypro.counter.model.controller;

import org.skypro.counter.model.article.Article;
import org.skypro.counter.model.basket.UserBasket;
import org.skypro.counter.model.product.Product;
import org.skypro.counter.model.search.SearchResult;
import org.skypro.counter.model.service.BasketService;
import org.skypro.counter.model.service.SearchService;
import org.skypro.counter.model.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;

    public ShopController(StorageService storageService, SearchService searchService) {
        this.storageService = storageService;
        this.searchService = searchService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getAllProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getAllArticles();
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam("pattern") String pattern) {
        return searchService.search(pattern);
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.addProduct(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }

    @Autowired
    private BasketService basketService;
}
