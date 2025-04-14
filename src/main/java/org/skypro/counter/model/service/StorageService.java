package org.skypro.counter.model.service;

import org.skypro.counter.model.article.Article;
import org.skypro.counter.model.product.DiscountedProduct;
import org.skypro.counter.model.product.FixPriceProduct;
import org.skypro.counter.model.product.Product;
import org.skypro.counter.model.product.SimpleProduct;
import org.skypro.counter.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> availableProducts;
    private final Map<UUID, Article> availableArticles;

    public StorageService() {
        this.availableProducts = new HashMap<>();
        this.availableArticles = new HashMap<>();
        this.createTestData();
    }

    public Collection<Product> getAllProducts() {
        return availableProducts.values();
    }

    public Collection<Article> getAllArticles() {
        return availableArticles.values();
    }

    public Collection<Searchable> getSearchableItems() {
        List<Searchable> searchableItems = new ArrayList<>();
        searchableItems.addAll(this.availableProducts.values());
        searchableItems.addAll(this.availableArticles.values());
        return searchableItems;
    }

    private void createTestData() {
        Product sugar = new SimpleProduct(UUID.randomUUID(),"Сахар", 50);
        Product milk = new SimpleProduct(UUID.randomUUID(),"Молоко", 80);
        Product milkOld = new SimpleProduct(UUID.randomUUID(),"Молоко топлёное", 135);
        Product cream = new SimpleProduct(UUID.randomUUID(),"Сметана", 120);
        Product butter = new FixPriceProduct(UUID.randomUUID(),"Масло");
        Product mayonnaise = new FixPriceProduct(UUID.randomUUID(),"Майонез");
        Product bread = new FixPriceProduct(UUID.randomUUID(),"Хлеб");
        Product sausage = new DiscountedProduct(UUID.randomUUID(),"Колбаса", 280, 20);
        Product cheese = new DiscountedProduct(UUID.randomUUID(),"Сыр", 250, 15);
        Product egg = new DiscountedProduct(UUID.randomUUID(),"Яйцо", 160, 10);
        Article aboutWhiteBread = new Article(UUID.randomUUID(),"Хлеб белый", "Часто используется для бутербродов");
        Article aboutBread = new Article(UUID.randomUUID(),"Хлеб", "Хлеб всему голова");
        Article aboutButter = new Article(UUID.randomUUID(),"Масло", "Бутерброд вкуснее с маслом");
        Article aboutCheese = new Article(UUID.randomUUID(),"Сыр", "Сыр бывает с плесенью");
        Article aboutMilk = new Article(UUID.randomUUID(),"Молоко пастеризованное", "Специально обработанное молоко");
        this.availableProducts.put(sugar.getId(), sugar);
        this.availableProducts.put(milk.getId(), milk);
        this.availableProducts.put(milkOld.getId(), milkOld);
        this.availableProducts.put(cream.getId(), cream);
        this.availableProducts.put(butter.getId(), butter);
        this.availableProducts.put(mayonnaise.getId(), mayonnaise);
        this.availableProducts.put(bread.getId(), bread);
        this.availableProducts.put(sausage.getId(), sausage);
        this.availableProducts.put(cheese.getId(), cheese);
        this.availableProducts.put(egg.getId(), egg);
        this.availableArticles.put(aboutWhiteBread.getId(), aboutWhiteBread);
        this.availableArticles.put(aboutBread.getId(), aboutBread);
        this.availableArticles.put(aboutButter.getId(), aboutButter);
        this.availableArticles.put(aboutCheese.getId(), aboutCheese);
        this.availableArticles.put(aboutMilk.getId(), aboutMilk);
    }
}
