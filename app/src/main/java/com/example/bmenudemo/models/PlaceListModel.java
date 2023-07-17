package com.example.bmenudemo.models;

import java.util.ArrayList;

public class PlaceListModel {

    private String placeid;


    private String placename;

    private String description;

    private String placelocation;

    private String photo;

    private String latitude;

    private String longitude;

    private String categoryid;

    private String stateid;

    private String dateAdded;

    private String likes;

//    int likeCount;
//    ArrayList<LikeData> likeData;

    public PlaceListModel( String placename, String description,  String placelocation,  String photo,String likes) {

        this.placename = placename;

        this.description = description;

        this.placelocation = placelocation;

        this.photo = photo;

        this.likes=likes;
    }

    public PlaceListModel(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

   /* public PlaceListModel(String placeid, String placename, String description, String placelocation, String photo, String latitude, String longitude, String categoryid, String stateid) {
        this.placeid = placeid;
        this.placename = placename;
        this.description = description;
        this.placelocation = placelocation;
        this.photo = photo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categoryid = categoryid;
        this.stateid = stateid;

    }
*/
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

//    public int getLikeCount() {
//        return likeCount;
//    }
//
//    public void setLikeCount(int likeCount) {
//        this.likeCount = likeCount;
//    }
//
//    public ArrayList<LikeData> getLikeData() {
//        return likeData;
//    }
//
//    public void setLikeData(ArrayList<LikeData> likeData) {
//        this.likeData = likeData;
//    }


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getStateid() {
        return stateid;
    }

    public void setStateid(String stateid) {
        this.stateid = stateid;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
