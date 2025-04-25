package org.skypro.counter.model.product;

import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> productMap = new TreeMap<>();

    public void addProduct(Product product) {
        LinkedList<Product> products = new LinkedList<>();
        if (!productMap.containsKey(product.getName())) {
            products = new LinkedList<>();
            productMap.put(product.getName(), products);
        } else {
            productMap.get(product.getName());
        }
        products.add(product);
    }

    public LinkedList<Product> deleteProductByName(String name) {
        if (productMap.containsKey(name)) {
            List<Product> deletedProducts = productMap.remove(name);
            return new LinkedList<>(deletedProducts);
        }
        return new LinkedList<>();
    }

    public double getTotalCost() {
        return productMap.values()
                .stream()
                .flatMap(Collection::stream)
                .mapToInt(Product -> (int) Math.round(Product.getPrice())).sum();
    }

    private int getSpecialCount() {
        return (int) productMap.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(Product::isSpecial)
                .count();
    }

    public void printBasket() {
        if (productMap.isEmpty()) {
            System.out.println("В корзине пусто ");
        } else {
            productMap.values()
                    .stream()
                    .flatMap(Collection::stream)
                    .forEach(System.out::println);
            System.out.println("Итого: " + getTotalCost());
            System.out.println("Специальных товаров: " + getSpecialCount());
        }
    }

    public boolean findByName(String name) {
        return productMap.containsKey(name);
    }

    public void clearBasket() {
        productMap.clear();
    }
}

