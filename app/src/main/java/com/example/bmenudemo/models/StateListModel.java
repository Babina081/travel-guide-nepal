package com.example.bmenudemo.models;

public class StateListModel {

    private String stateid;

    private String statename;

    private String stateimage;

    public StateListModel(String statename, String stateimage) {

        this.statename = statename;
        this.stateimage = stateimage;

    }

    public String getStateid() {
        return stateid;
    }

    public void setStateid(String stateid) {
        this.stateid = stateid;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public String getStateimage() {
        return stateimage;
    }

    public void setStateimage(String stateimage) {
        this.stateimage = stateimage;
    }
}
