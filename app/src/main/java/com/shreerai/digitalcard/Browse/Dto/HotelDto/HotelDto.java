package com.shreerai.digitalcard.Browse.Dto.HotelDto;

import android.graphics.Bitmap;

public class HotelDto {
    private int id;
    private Bitmap image;

    public HotelDto(Bitmap image) {
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
