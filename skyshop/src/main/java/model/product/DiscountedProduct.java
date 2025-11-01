package model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    public DiscountedProduct(UUID uuid, String ноутбук, int i, int i1) {
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

    public static class SimpleProduct extends Product {
        public SimpleProduct(UUID uuid, String мышь, int i) {
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
}