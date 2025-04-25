package org.skypro.counter.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.counter.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final String articleTitle;
    private final String articleText;
    private final UUID id;

    public Article(UUID id, String articleTitle, String articleText) {
        this.articleTitle = articleTitle;
        this.articleText = articleText;
        this.id = id;
    }

    public String getName() {
        return articleTitle;
    }

    @Override
    public String toString() {
        return articleTitle + "\n" + articleText;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return toString();
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(articleTitle, article.articleTitle) && Objects.equals(articleText, article.articleText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleTitle, articleText);
    }

    @Override
    public UUID getId() {
        return id;
    }
}
