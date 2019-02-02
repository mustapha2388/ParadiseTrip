package com.abdelouahad.mustapha.myapp.model;

import android.widget.ImageView;

public class Compagny {
    private String price;
    private String destination;
    private String start_date;
    private String return_date;
    private ImageView imageView;
    private String ImageBase64;
    private String name;
    private String start_hour;
    private String return_hour;
    private String tel;
    private String duration;

    public Compagny(String price, String destination, String start_date, String return_date, ImageView imageView, String imageBase64, String name, String start_hour, String return_hour, String tel, String duration) {
        this.price = price;
        this.destination = destination;
        this.start_date = start_date;
        this.return_date = return_date;
        this.imageView = imageView;
        ImageBase64 = imageBase64;
        this.name = name;
        this.start_hour = start_hour;
        this.return_hour = return_hour;
        this.tel = tel;
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getImageBase64() {
        return ImageBase64;
    }

    public void setImageBase64(String imageBase64) {
        ImageBase64 = imageBase64;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(String start_hour) {
        this.start_hour = start_hour;
    }

    public String getReturn_hour() {
        return return_hour;
    }

    public void setReturn_hour(String return_hour) {
        this.return_hour = return_hour;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
