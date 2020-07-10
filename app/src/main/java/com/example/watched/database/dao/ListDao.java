package com.example.watched.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.watched.database.entity.ListEntity;

import java.util.List;

/**
 * https://developer.android.com/topic/libraries/architecture/room.html#no-object-references
 */
@Dao
public interface ListDao {

    @Query("SELECT * FROM List WHERE name = :name")
    LiveData<List<ListEntity>> getByName(String name);

    @Query("SELECT * FROM List WHERE owner = :owner")
    LiveData<ListEntity> getByOwner(String owner);

    @Query("SELECT * FROM List")
    LiveData<List<ListEntity>> getAll();

    @Insert
    long insert(ListEntity list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ListEntity> accounts);

    @Update
    void update(ListEntity list);

    @Delete
    void delete(ListEntity list);

    @Query("DELETE FROM List")
    void deleteAll();


}
