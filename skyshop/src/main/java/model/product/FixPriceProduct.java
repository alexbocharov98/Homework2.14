package model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    public FixPriceProduct(UUID uuid, String чехол) {
        super();
    }

    @Override
    public int getProductPrice() {
        return 0;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}