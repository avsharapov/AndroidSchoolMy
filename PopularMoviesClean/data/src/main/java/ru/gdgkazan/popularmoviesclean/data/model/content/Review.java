package ru.gdgkazan.popularmoviesclean.data.model.content;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * @author Artur Vasilov
 */
public class Review extends RealmObject {

    @SerializedName("author")
    private String mAuthor;

    @SerializedName("content")
    private String mContent;

    public Review() {
    }

    public Review(String author, String content) {
        mAuthor = author;
        mContent = content;
    }

    public Review(Parcel in) {
        mAuthor = in.readString();
        mContent = in.readString();
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
