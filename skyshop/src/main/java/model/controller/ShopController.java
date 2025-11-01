package model.controller;

import model.basket.UserBasket;
import model.search.SearchResult;
import model.search.SearchService;
import model.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.UUID;

import model.service.StorageService;
import model.product.Product;
import model.article.Article;



@RestController
public class ShopController {

    private final StorageService storageService;


    @Autowired
    public ShopController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getAllProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getAllArticles();
    }

    @RestController
    public static class shopController {
        private final SearchService searchService;
        private final BasketService basketService;

        public shopController(SearchService searchService, BasketService basketService) {
            this.searchService = searchService;
            this.basketService = basketService;
        }

        @GetMapping("/search")
        public Collection<SearchResult> search(@RequestParam String pattern) {
            return searchService.search(pattern);
        }

        @GetMapping("/basket/{id}")
        public String addProduct(@PathVariable("id") UUID id) {
            basketService.addProductToBasket(id);
            return "Продукт успешно добавлен";
        }

        @GetMapping("/basket")
        public UserBasket getUserBasket() {
            return basketService.getUserBasket();
        }
    }



}