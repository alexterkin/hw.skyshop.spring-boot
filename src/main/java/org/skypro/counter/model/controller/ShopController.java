package org.skypro.counter.model.controller;

import org.skypro.counter.model.article.Article;
import org.skypro.counter.model.product.Product;
import org.skypro.counter.model.search.SearchResult;
import org.skypro.counter.model.service.SearchService;
import org.skypro.counter.model.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
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
}
