package com.example.bmenudemo.models;

public class PlaceListModel {

    private String placeid;


    private String placename;

    private String description;

    private String placelocation;

    private String photo;

    private String likes;

    public PlaceListModel( String placename, String description,  String placelocation,  String photo,String likes) {

        this.placename = placename;

        this.description = description;

        this.placelocation = placelocation;

        this.photo = photo;

        this.likes=likes;
    }


    public String getPlaceid() {
        return placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }


    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPlacelocation() {
        return placelocation;
    }

    public void setPlacelocation(String placelocation) {
        this.placelocation = placelocation;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
