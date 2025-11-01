package model.controller;

import model.search.SearchResult;
import model.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
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

        public shopController(SearchService searchService) {
            this.searchService = searchService;
        }

        @GetMapping("/search")
        public Collection<SearchResult> search(@RequestParam String pattern) {
            return searchService.search(pattern);
        }
    }
}