package model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> products = new ConcurrentHashMap<>();

    public void addProduct(UUID id) {
        products.merge(id, 1, Integer::sum);
    }

    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }



}
