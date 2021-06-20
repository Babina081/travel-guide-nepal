
package com.example.bmenudemo.models;



public class CategoryListModel {

    private String categoryid;
    private String categoryname;

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
}
