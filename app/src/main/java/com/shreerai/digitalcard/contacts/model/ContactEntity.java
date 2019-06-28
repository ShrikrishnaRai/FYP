package com.shreerai.digitalcard.contacts.model;

public class ContactEntity {
    private String id;
    private String firstname;
    private String lastname;
    private String image;
    private String position;
    private String company;

    public ContactEntity() {
    }

    public ContactEntity(String id, String firstname, String lastname, String image, String position, String company) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.image = image;
        this.position = position;
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
