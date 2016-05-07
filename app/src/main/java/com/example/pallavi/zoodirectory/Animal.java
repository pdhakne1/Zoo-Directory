package com.example.pallavi.zoodirectory;

import java.io.Serializable;

/**
 * Created by pallavi on 2/1/16.
 */
public class Animal implements Serializable {

    private String name;
    private String image;
    private String description;

    public Animal(String name, String image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
