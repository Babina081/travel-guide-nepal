
package com.example.bmenudemo.models;


import java.util.List;

public class CategoryListModel {

    private String categoryid;
    private String categoryname;
//    private List<PlaceListModel> placeListModels;

//    public CategoryListModel( String categoryname, List<PlaceListModel> placeListModels) {
//        this.categoryname = categoryname;
//        this.placeListModels = placeListModels;
//    }

    public CategoryListModel() {
    }

    public CategoryListModel(String categoryid, String categoryname) {
        this.categoryid = categoryid;
        this.categoryname = categoryname;
    }

    public CategoryListModel(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

//    public List<PlaceListModel> getPlaceListModels() {
//        return placeListModels;
//    }
//
//    public void setPlaceListModels(List<PlaceListModel> placeListModels) {
//        this.placeListModels = placeListModels;
//    }
}
