package ru.gdgkazan.popularmoviesclean.domain.model;

import java.io.Serializable;

/**
 * @author Artur Vasilov
 */
public class Review implements Serializable {

    private String mAuthor;
    private String mContent;

    public Review() {
    }

    public Review(String author, String content) {
        mAuthor = author;
        mContent = content;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
