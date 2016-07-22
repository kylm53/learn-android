package com.kkk.mvpdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CTC on 2016/7/21.
 */
public class Article implements Parcelable {
    public String title;
    public String link;

    public Article() {
    }

    public Article(String title, String link) {
        this.title = title;
        this.link = link;
    }


    protected Article(Parcel in) {
        title = in.readString();
        link = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(link);
    }
}
