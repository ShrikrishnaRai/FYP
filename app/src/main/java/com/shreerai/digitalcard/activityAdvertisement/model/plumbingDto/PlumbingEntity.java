package com.shreerai.digitalcard.activityAdvertisement.model.plumbingDto;

import android.graphics.Bitmap;

public class PlumbingEntity {
    private int id;
    private Bitmap image;

    public PlumbingEntity(Bitmap image) {
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
