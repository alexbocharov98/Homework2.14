package model.service;

import model.article.Article;
import model.product.DiscountedProduct;
import model.product.FixPriceProduct;
import model.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        products = new HashMap<>();
        articles = new HashMap<>();
        loadTestData();
    }
    private void loadTestData() {

        Product p1 = new DiscountedProduct.SimpleProduct(UUID.randomUUID(), "Мышь", 1500);
        Product p2 = new DiscountedProduct(UUID.randomUUID(), "Ноутбук", 50000, 10);
        Product p3 = new FixPriceProduct(UUID.randomUUID(), "Чехол");

        products.put(p1.getId(), p1);
        products.put(p2.getId(), p2);
        products.put(p3.getId(), p3);


        Article a1 = new Article(UUID.randomUUID(), "Обзор мыши", "Обзор игровой мыши с подсветкой");
        Article a2 = new Article(UUID.randomUUID(), "Преимущества ноутбука", "Все преимущества современного ноутбука");
        Article a3 = new Article(UUID.randomUUID(), "Выбор чехла", "Как выбрать чехол для телефона");

        articles.put(a1.getId(), a1);
        articles.put(a2.getId(), a2);
        articles.put(a3.getId(), a3);
    }

    public Collection<Product> getAllProducts() {
        return Collections.unmodifiableCollection(products.values());
    }

    public Collection<Article> getAllArticles() {
        return Collections.unmodifiableCollection(articles.values());
    }
}