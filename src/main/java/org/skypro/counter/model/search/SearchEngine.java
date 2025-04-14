package org.skypro.counter.model.search;

import org.skypro.counter.model.exception.BestResultNotFoundException;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class SearchEngine {
    private final Set<Searchable> searchables;

    public SearchEngine() {
        this.searchables = new HashSet<>();
    }

    public Set<Searchable> search(String clientRequest) {
        return searchables.stream()
                .filter(searchable -> searchable.getSearchTerm().contains(clientRequest))
                .collect(Collectors.toCollection(() -> new TreeSet<>(new SearchEngineComparator())));
    }

    public void add(Searchable searchable) {
        if (searchable != null) {
            searchables.add(searchable);
        }
    }

    public void getBestMatch(String search) throws BestResultNotFoundException {
        Searchable candidate = null;
        int candidateCount = 0;
        for(Searchable searchable : searchables) {
            if(searchable != null) {
                int count = countMatches(searchable.getSearchTerm(), search);
                if(count > candidateCount) {
                    candidate = searchable;
                    candidateCount = count;
                }
            }
        }
        if(candidate == null) {
            throw new BestResultNotFoundException("Результат не найден");
        }
    }

    private int countMatches(String source, String search) {
        int count = 0;
        int index = 0;
        while ((index = source.indexOf(search, index)) >= 0) {
            count++;
            index = index + search.length();
        }
        return count;
    }
}
