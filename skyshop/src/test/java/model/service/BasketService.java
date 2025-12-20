package model.service;

import model.basket.ProductBasket;
import model.basket.UserBasket;
import model.product.Product;
import model.exception.NoSuchProductException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @ExtendWith(MockitoExtension.class)
    class BasketServiceTest {

        @Mock
        private ProductBasket productBasket;

        @Mock
        private StorageService storageService;

        @InjectMocks
        private BasketService basketService;
    }
}





