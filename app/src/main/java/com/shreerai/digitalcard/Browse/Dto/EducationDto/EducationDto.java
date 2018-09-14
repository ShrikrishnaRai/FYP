package com.shreerai.digitalcard.Browse.Dto.EducationDto;

import android.graphics.Bitmap;

public class EducationDto {
    private int id;
    private String name;
    private String address;
    private String email;
    private long phone;

    public EducationDto(String name, String address, String email, long phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public EducationDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
