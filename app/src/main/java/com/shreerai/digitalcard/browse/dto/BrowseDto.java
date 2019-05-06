package com.shreerai.digitalcard.browse.dto;

import android.graphics.Bitmap;

public class BrowseDto {
    private int id;
    private Bitmap image;
    private String title;

    public BrowseDto(Bitmap image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
