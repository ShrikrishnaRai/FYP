package com.shreerai.digitalcard.activityAdvertisement.model.hotelDto;

import android.graphics.Bitmap;

public class HotelEntity {
    private int id;
    private Bitmap image;

    public HotelEntity(Bitmap image) {
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
