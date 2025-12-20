package model.basket;

import java.util.List;
import java.util.Objects;

public final class UserBasket {
    public List<BasketItem> items = List.of();
    private final int total;

    public UserBasket() {
        this.items = Objects.requireNonNull(items);
        this.total = items.stream()
                .mapToInt(item -> item.getProduct().getProductPrice() * item.getQuantity())
                .sum();
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }
}

