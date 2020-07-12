package com.example.watched.database.dao;

import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.watched.database.entity.EpisodeEntity;
import com.example.watched.database.pojo.TvShowWithEpisodes;

import java.util.List;

/**
 * https://developer.android.com/topic/libraries/architecture/room.html#no-object-references
 */
@Dao
public interface EpisodeDao {

    @Query("SELECT * FROM Episodes WHERE seen = :seen")
    LiveData<List<EpisodeEntity>> getAllSeen(boolean seen);

    @Query("SELECT * FROM Episodes WHERE seen != :seen")
    LiveData<List<EpisodeEntity>> getAllNotSeen(boolean seen);

    @Query("SELECT * FROM Episodes WHERE id = :id")
    LiveData<EpisodeEntity> getById(Long id);

    @Query("SELECT * FROM Episodes")
    LiveData<List<EpisodeEntity>> getAll();

    @Query("SELECT * FROM Episodes WHERE TvShow= :show" )
    LiveData<List<EpisodeEntity>> getAllEpisodeFromShow(String show);

    @Insert
    long insert(EpisodeEntity Episode) throws SQLiteConstraintException;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<EpisodeEntity> Episodes);

    @Update
    void update(EpisodeEntity Episode);

    @Delete
    void delete(EpisodeEntity Episode);

    @Query("DELETE FROM Episodes")
    void deleteAll();
}
