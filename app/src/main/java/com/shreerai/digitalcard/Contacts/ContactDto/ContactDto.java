package com.shreerai.digitalcard.Contacts.ContactDto;

import android.graphics.Bitmap;

public class ContactDto {
    private int id;
    private Bitmap image;
    private String name;
    private String type;

    public ContactDto(Bitmap image, String name, String type) {
        this.image = image;
        this.name = name;
        this.type = type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
