package model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private String productName = "";


    public Product() {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Поле с названием не может быть пустым");
        }
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }


    @JsonIgnore
    public String getSearchTerm() {
        return getProductName();
    }

    @JsonIgnore
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String getName() {
        return getProductName();
    }

    public abstract int getProductPrice();

    public abstract boolean isSpecial();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productName);
    }
    private final UUID id = UUID.randomUUID();

    @Override
    public UUID getId() {
        return id;
    }

}