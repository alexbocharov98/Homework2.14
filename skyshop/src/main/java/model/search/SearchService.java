package model.search;

import model.search.SearchResult;
import org.springframework.stereotype.Service;
import model.service.StorageService;
import model.search.Searchable;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    private Collection<Searchable> getAllSearchables() {
        return Stream.concat(
                storageService.getAllProducts().stream(),
                storageService.getAllArticles().stream()
        ).collect(Collectors.toList());
    }

    public Collection<SearchResult> search(String pattern) {
        return getAllSearchables().stream()
                .filter(item -> item.getName().contains(pattern))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}