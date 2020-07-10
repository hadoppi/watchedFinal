package com.example.watched.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;


@Entity(tableName = "List")
public class ListEntity {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String name;
    @NonNull
    private String favoriteShows;
    @NonNull
    private String owner;

    public ListEntity(String name, String owner, String favoriteShows) {
        this.name = name;
        this.owner = owner;
        this.favoriteShows = favoriteShows;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getFavoriteShows() {
        return favoriteShows;
    }

    public void setFavoriteShows(@NonNull String favoriteShows) {
        this.favoriteShows = favoriteShows;
    }

    @NonNull
    public String getOwner() {
        return owner;
    }

    public void setOwner(@NonNull String owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof ListEntity)) return false;
        ListEntity o = (ListEntity) obj;
        return o.getName().equals(this.getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
