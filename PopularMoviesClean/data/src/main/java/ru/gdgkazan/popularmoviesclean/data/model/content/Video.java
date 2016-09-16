package ru.gdgkazan.popularmoviesclean.data.model.content;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * @author Artur Vasilov
 */
public class Video extends RealmObject {

    @SerializedName("key")
    private String mKey;

    @SerializedName("name")
    private String mName;

    public Video() {
    }

    public Video(String key, String name) {
        mKey = key;
        mName = name;
    }

    public Video(Parcel in) {
        mKey = in.readString();
        mName = in.readString();
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
