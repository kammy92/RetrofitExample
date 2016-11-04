package com.karman.model;

import android.util.Log;

/**
 * Created by Belal on 11/3/2015.
 */
public class Services {

    //Variables that are in our json
    private int app_flow;
    private int role_id;
    private String role_name;
    private String image;

    //Getters and setters
    public int getApp_flow() {
        return app_flow;
    }

    public void setApp_flow(int app_flow) {
        this.app_flow = app_flow;
        Log.d ("app_flow", "" + app_flow);
    }

    public int getRole_id () {
        return role_id;
    }

    public void setRole_id (int role_id) {
        this.role_id = role_id;
        Log.d ("role_id", "" + role_id);
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
        Log.d ("role_name", role_name);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        Log.d ("image", image);
    }

}
