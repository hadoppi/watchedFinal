package com.example.watched.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Show")
public class AccountEntity {
    @PrimaryKey @NonNull
    private String name;

    public AccountEntity() {
    }

    public AccountEntity(String name) {
        this.name = name;
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
        if (!(obj instanceof AccountEntity)) return false;
        AccountEntity o = (AccountEntity) obj;
        return o.getName().equals(this.getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
