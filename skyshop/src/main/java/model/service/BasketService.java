package model.service;

import model.basket.ProductBasket;
import model.basket.UserBasket;
import model.product.Product;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.awt.*;
import java.util.Optional;
import java.util.UUID;


public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        Optional<Product> productOpt = storageService.getProductById(id);
        if (!productOpt.isPresent()) {
            throw new IllegalArgumentException("Товар с таким id не найден: " + id);
        }
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        return new UserBasket();
    }
}



