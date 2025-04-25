package org.skypro.counter;

import org.skypro.counter.model.article.Article;
import org.skypro.counter.model.exception.BestResultNotFoundException;
import org.skypro.counter.model.product.*;
import org.skypro.counter.model.search.SearchEngine;
import org.skypro.counter.model.search.Searchable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class CounterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CounterServiceApplication.class, args);
		ProductBasket basket = new ProductBasket();
		basket.addProduct(new SimpleProduct(UUID.randomUUID(),"Сахар", 50));
		basket.addProduct(new SimpleProduct(UUID.randomUUID(),"Молоко", 80));
		basket.addProduct(new SimpleProduct(UUID.randomUUID(),"Молоко топлёное", 135));
		basket.addProduct(new SimpleProduct(UUID.randomUUID(),"Сметана", 120));
		basket.addProduct(new FixPriceProduct(UUID.randomUUID(),"Масло"));
		basket.addProduct(new FixPriceProduct(UUID.randomUUID(),"Майонез"));
		basket.addProduct(new FixPriceProduct(UUID.randomUUID(),"Хлеб"));
		basket.addProduct(new DiscountedProduct(UUID.randomUUID(),"Колбаса", 280, 20));
		basket.addProduct(new DiscountedProduct(UUID.randomUUID(),"Сыр", 250, 15));
		basket.addProduct(new DiscountedProduct(UUID.randomUUID(),"Яйцо", 160, 10));

		basket.printBasket();

		SearchEngine searchEngine = new SearchEngine();
		Searchable sugar = new SimpleProduct(UUID.randomUUID(),"Сахар", 50);
		Searchable milk = new SimpleProduct(UUID.randomUUID(),"Молоко", 80);
		Searchable milkOld = new SimpleProduct(UUID.randomUUID(),"Молоко топлёное", 135);
		Searchable cream = new SimpleProduct(UUID.randomUUID(),"Сметана", 120);
		Searchable butter = new FixPriceProduct(UUID.randomUUID(),"Масло");
		Searchable mayonnaise = new FixPriceProduct(UUID.randomUUID(),"Майонез");
		Searchable bread = new FixPriceProduct(UUID.randomUUID(),"Хлеб");
		Searchable sausage = new DiscountedProduct(UUID.randomUUID(),"Колбаса", 280, 20);
		Searchable cheese = new DiscountedProduct(UUID.randomUUID(),"Сыр", 250, 15);
		Searchable egg = new DiscountedProduct(UUID.randomUUID(),"Яйцо", 160, 10);
		searchEngine.add(sugar);
		searchEngine.add(milk);
		searchEngine.add(milkOld);
		searchEngine.add(cream);
		searchEngine.add(butter);
		searchEngine.add(mayonnaise);
		searchEngine.add(bread);
		searchEngine.add(sausage);
		searchEngine.add(cheese);
		searchEngine.add(egg);

		Article aboutWhiteBread = new Article(UUID.randomUUID(),"Хлеб белый", "Часто используется для бутербродов");
		Article aboutBread = new Article(UUID.randomUUID(),"Хлеб", "Хлеб всему голова");
		Article aboutButter = new Article(UUID.randomUUID(),"Масло", "Бутерброд вкуснее с маслом");
		Article aboutCheese = new Article(UUID.randomUUID(),"Сыр", "Сыр бывает с плесенью");
		Article aboutMilk = new Article(UUID.randomUUID(),"Молоко пастеризованное", "Специально обработанное молоко");
		searchEngine.add(aboutWhiteBread);
		searchEngine.add(aboutBread);
		searchEngine.add(aboutButter);
		searchEngine.add(aboutCheese);
		searchEngine.add(aboutMilk);

		System.out.println("----------");

		String clientRequest1 = "Хлеб";
		System.out.println("Поиск " + clientRequest1 + ": " + searchEngine.search(clientRequest1));
		String clientRequest2 = "Сыр";
		System.out.println("Поиск " + clientRequest2 + ": " + searchEngine.search(clientRequest2));
		String clientRequest3 = "Одежда";
		System.out.println("Поиск " + clientRequest3 + ": " + searchEngine.search(clientRequest3));
		String clientRequest4 = "Бутерброд";
		System.out.println("Поиск " + clientRequest4 + ": " + searchEngine.search(clientRequest4));

		System.out.println("----------");

		try {
			basket.addProduct(new SimpleProduct(UUID.randomUUID(),"  ", 0));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			basket.addProduct(new SimpleProduct(UUID.randomUUID(),"Яблоко", -150));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			basket.addProduct(new DiscountedProduct(UUID.randomUUID(),"Картофель", 85, 120));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("----------");

		try {
			searchEngine.getBestMatch("витамины");
		} catch (BestResultNotFoundException e) {
			System.out.println(e.getMessage());
		}

		LinkedList<Product> deletedProducts = basket.deleteProductByName("Сыр");
		for (Product product: deletedProducts) {
			System.out.println(product);
		}

		System.out.println("----------");

		basket.printBasket();

		System.out.println("----------");

		String clientRequest = "Молоко";
		Set<Searchable> searchResults = searchEngine.search(clientRequest);
		for (Searchable entry : searchResults) {
			System.out.println("Имя продукта: " + entry.getName());
		}
	}
}
