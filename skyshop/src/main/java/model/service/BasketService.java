package model.service;

import model.basket.UserBasket;

import java.util.UUID;

public class BasketService {
    public void addProductToBasket(UUID id) {
    }

    public UserBasket getUserBasket() {
        return getBasket();
    }

    private static UserBasket getBasket() {
        return null;
    }
}
