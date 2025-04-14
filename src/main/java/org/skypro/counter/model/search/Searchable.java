package org.skypro.counter.model.search;

import java.util.UUID;

public interface Searchable {
    String getSearchTerm();
    String getContentType();
    String getName();
    default String getStringRepresentation() {
        return this.getClass().getSimpleName() + "-" + getContentType();
    }

    UUID getId();

}