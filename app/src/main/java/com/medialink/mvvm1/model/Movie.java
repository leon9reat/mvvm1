package com.medialink.mvvm1.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private int posterId;

    public Movie(String name, String description, int posterId) {
        this.name = name;
        this.description = description;
        this.posterId = posterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPosterId() {
        return posterId;
    }

    public void setPosterId(int posterId) {
        this.posterId = posterId;
    }
}
