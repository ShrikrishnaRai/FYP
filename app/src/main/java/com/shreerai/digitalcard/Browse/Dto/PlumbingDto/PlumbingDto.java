package com.shreerai.digitalcard.Browse.Dto.PlumbingDto;

import android.graphics.Bitmap;

public class PlumbingDto {
    private int id;
    private Bitmap image;

    public PlumbingDto(Bitmap image) {
        this.image = image;
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
}
