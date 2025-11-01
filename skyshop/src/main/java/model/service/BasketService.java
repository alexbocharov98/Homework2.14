package model.service;

import model.basket.ProductBasket;
import model.basket.UserBasket;
import model.product.Product;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import model.exception.NoSuchProductException;
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

    public void addProductToBasket(UUID productId) {
        Optional<Product> productOpt = storageService.getProductById(productId);
        if (!productOpt.isPresent()) {
            throw new NoSuchProductException("Товар с id " + productId + " не найден");
        }
        productBasket.addProduct(productId);
    }

    public UserBasket getUserBasket() {
        return new UserBasket();
    }
}



