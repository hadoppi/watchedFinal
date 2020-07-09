package com.example.watched.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import android.database.sqlite.SQLiteConstraintException;

import java.util.List;

import com.example.watched.database.entity.ClientEntity;

/**
 * https://developer.android.com/topic/libraries/architecture/room.html#no-object-references
 */
@Dao
public interface ClientDao {

    @Query("SELECT * FROM clients WHERE email = :id")
    LiveData<ClientEntity> getById(String id);

    @Query("SELECT * FROM clients")
    LiveData<List<ClientEntity>> getAll();



    @Insert
    long insert(ClientEntity client) throws SQLiteConstraintException;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ClientEntity> clients);

    @Update
    void update(ClientEntity client);

    @Delete
    void delete(ClientEntity client);

    @Query("DELETE FROM clients")
    void deleteAll();
}
