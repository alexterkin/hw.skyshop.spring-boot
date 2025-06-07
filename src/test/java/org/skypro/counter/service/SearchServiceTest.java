package org.skypro.counter.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.counter.model.product.SimpleProduct;
import org.skypro.counter.model.search.SearchResult;
import org.skypro.counter.model.search.Searchable;
import org.skypro.counter.model.service.SearchService;
import org.skypro.counter.model.service.StorageService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    void givenEmptyStorage_whenFind_thenReturnEmptyList() {
        List<SearchResult> results = searchService.search("а");
        Assertions.assertTrue(results.isEmpty());

    }

    @Test
    void givenNonEmptyStorage_whenFind_thenReturnEmptyList() {
        Collection<Searchable> searchables = new ArrayList<>();
        searchables.add(new SimpleProduct(UUID.randomUUID(), "Масло", 125));
        when(storageService.getSearchableItems()).thenReturn(searchables);
        List<SearchResult> results = searchService.search("р");
        Assertions.assertTrue(results.isEmpty());
    }

    @Test
    void givenNonEmptyStorage_whenFind_thenReturnNonEmptyList() {
        Collection<Searchable> searchables = new ArrayList<>();
        searchables.add(new SimpleProduct(UUID.randomUUID(), "Масло", 125));
        when(storageService.getSearchableItems()).thenReturn(searchables);
        List<SearchResult> results = searchService.search("с");
        Assertions.assertEquals(1, results.size());
    }
}
