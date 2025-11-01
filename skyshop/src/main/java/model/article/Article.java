package model.article;


import model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private final String articleTitle;
    private final String articleText;

    public Article(UUID uuid, String articleTitle, String articleText) {
        this.articleTitle = articleTitle;
        this.articleText = articleText;
    }

    public String getTitle() {
        return articleTitle;
    }

    public String getText() {
        return articleText;
    }

    @Override
    public String getSearchTerm() {
        return this.toString(); // название + текст статьи
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return articleTitle;
    }

    @Override
    public String toString() {
        return articleTitle + "\n" + articleText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(articleTitle, article.articleTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(articleTitle);
    }

    private final UUID id = UUID.randomUUID();

    @Override
    public UUID getId() {
        return id;
    }
}