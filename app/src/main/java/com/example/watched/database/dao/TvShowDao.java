package com.example.watched.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.watched.database.entity.TvShowEntity;

/**
 * https://developer.android.com/topic/libraries/architecture/room.html#no-object-references
 */
@Dao
public interface TvShowDao {



    @Query("SELECT * FROM Show WHERE name = :name")
    LiveData<TvShowEntity> getByName(String name);


    @Query("SELECT * FROM Show ORDER BY name")
    LiveData<List<TvShowEntity>> getAll();


    @Insert
    long insert(TvShowEntity account);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TvShowEntity> accounts);

    @Update
    void update(TvShowEntity account);

    @Delete
    void delete(TvShowEntity account);

    @Query("DELETE FROM Show")
    void deleteAll();


}
