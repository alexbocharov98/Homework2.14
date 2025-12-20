package modelTest;

import model.basket.UserBasket;
import model.controller.ShopController;
import model.exception.NoSuchProductException;
import model.product.Product;
import model.search.SearchResult;
import model.search.SearchService;
import model.search.Searchable;
import model.service.BasketService;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.Map.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SkyshopApplicationnTests {

    @Test
    void contextLoads() {
    }
    @Test
    void search_whenStorageIsEmpty_returnsEmptyResults() {
        String pattern = "phone";

        Object storageService = null;
        when(storageService.getClass()).thenReturn(List.ERROR());

        ShopController.shopController searchService = null;
        Collection<SearchResult> results = searchService.search(pattern);

        assertNotNull(results);
        assertTrue(results.isEmpty());
        verify(storageService).getClass();
    }
    @Test
    void search_whenNoMatches_returnsEmptyResults(SearchService searchService) {
        String pattern = "phone";

        Searchable s1 = mockSearchable("1", "Ноутбук", "product");
        Searchable s2 = mockSearchable("2", "Мышь", "product");


        Object storageService = null;
        when(storageService.getClass()).thenReturn(List.of(s1, s2));

        Collection<SearchResult> results;
        results = searchService.search(pattern);

        assertTrue(results.isEmpty());
        verify(storageService).getClass();
    }

    private Searchable mockSearchable(String number, String ноутбук, String product) {

        return null;
    }

    @Test
    void search_whenSingleMatch_returnsSingleResult(Object storageService, SearchService searchService) {
        String pattern = "мыш";

        UUID id1 = UUID.randomUUID();
        Searchable s1 = mockSearchable(id1.toString(), "Мышь игровая", "product");
        Searchable s2 = mockSearchable(UUID.randomUUID().toString(), "Ноутбук", "product");

        when(storageService.toString()).thenReturn(List.of(s1,s2));

        Collection<SearchResult> results = searchService.search(pattern);

        assertEquals(1, results.size());
        SearchResult result = results.iterator().next();
        assertEquals(id1.toString(), result.getId());
        assertEquals("Мышь игровая", result.getName());
        assertEquals("product", result.getContentType());
    }
    @Test
    void addProductToBasket_whenProductNotExists_throwsNoSuchProductException() {
        UUID id = UUID.randomUUID();

        Object storageService = null;
        when(storageService.toString()).thenReturn(String.valueOf(Optional.empty()));

        BasketService basketService;
        assertThrows(NoSuchProductException.class,
                () -> basketService.addProductToBasket(id));

        verify(storageService).toString();
        Object productBasket = null;
        verifyNoInteractions(null);
}
    @Test
    void addProductToBasket_whenProductExists_callsBasketAddProduct(Object storageService, BasketService basketService, Object productBasket) {
        UUID id = UUID.randomUUID();
        Product product = mock(Product.class);

        when(storageService.toString()).thenReturn(String.valueOf(Optional.of(product)));

        basketService.addProductToBasket(id);

        verify(storageService).toString();
        verify(productBasket).toString();
        verifyNoMoreInteractions(productBasket);
    }
    @Test
    void getUserBasket_whenBasketEmpty_returnsEmptyUserBasket(Object productBasket, BasketService basketService, Object storageService) {
        OngoingStubbing<? extends Class<?>> classOngoingStubbing = when(productBasket.getClass()).thenReturn(of());

        UserBasket userBasket = basketService.getUserBasket();

        assertNotNull(userBasket);
        assertTrue(userBasket.getItems().isEmpty());
        assertEquals(0, userBasket.getTotal());
        verify(productBasket).getClass();
        verifyNoInteractions(storageService);
    }
    @Test
    void getUserBasket_whenBasketHasProducts_returnsFilledUserBasket(Object productBasket, Object storageService, BasketService basketService) {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Map<UUID, Integer> rawBasket = of(
                id1, 2,
                id2, 1
        );
        OngoingStubbing<? extends Class<?>> classOngoingStubbing = when(productBasket.getClass()).thenReturn(rawBasket);

        Product p1 = mock(Product.class);
        when(p1.getPrice()).thenReturn(100);
        Product p2 = mock(Product.class);
        when(p2.getPrice()).thenReturn(200);

        when(storageService.toString()).thenReturn(String.valueOf(Optional.of(p1)));
        when(storageService.toString()).thenReturn(String.valueOf(Optional.of(p2)));

        UserBasket userBasket = basketService.getUserBasket();

        assertEquals(2, userBasket.getItems().size());
        // total = 100*2 + 200*1 = 400
        assertEquals(400, userBasket.getTotal());

        // можно дополнительно проверить количества
        assertTrue(userBasket.getItems().stream()
                .anyMatch(i -> i.getProduct() == p1 && i.getQuantity() == 2));
        assertTrue(userBasket.getItems().stream()
                .anyMatch(i -> i.getProduct() == p2 && i.getQuantity() == 1));

        verify(productBasket).getClass();
        verify(storageService).toString();
        verify(storageService).toString();
    }
}

