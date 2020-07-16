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
    LiveData<ListEntity> getByName(String name);

    @Query("SELECT DISTINCT name FROM LIST")
    LiveData<List<String>> getAllName();

    @Query("SELECT * FROM List WHERE owner = :owner")
    LiveData<List<ListEntity>> getByOwner(String owner);

    @Query("SELECT * FROM List")
    LiveData<List<ListEntity>> getAll();

    @Insert
    long insert(ListEntity list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ListEntity> accounts);

    @Update
    void update(ListEntity list);

    @Query("DELETE FROM List where name =:name")
    void deleteAllList(String name);

    @Query("DELETE FROM List where favoriteShows = :favShow AND name= :name")
    void delete(String name, String favShow);

    @Query("DELETE FROM List")
    void deleteAll();


}
