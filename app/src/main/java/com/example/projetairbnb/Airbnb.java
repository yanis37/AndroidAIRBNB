package com.example.projetairbnb;

public class Airbnb {

    private String city;
    private String property;
    private String name;
    private String price;
    private String picture;

    public Airbnb(String name, String city, String property, String price, String picture){
        this.name = name ;
        this.city = city ;
        this.property = property ;
        this.price = price ;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }
    public String getProperty() {
        return property;
    }
    public String getPrice() {
        return price;
    }
    public String getPicture() {
        return picture;
    }



}
