package com.example.MeetEat;

import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("id") private int Id;
    @SerializedName("name") private String Name;
    @SerializedName("email") private String Email;

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }
}

