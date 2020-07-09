package com.example.watched.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import com.example.watched.database.entity.AccountEntity;

/**
 * https://developer.android.com/topic/libraries/architecture/room.html#no-object-references
 */
@Dao
public interface AccountDao {



    @Query("SELECT * FROM Show WHERE name = :name")
    public abstract LiveData<AccountEntity> getByName(String name);


    @Query("SELECT * FROM Show")
    public abstract LiveData<List<AccountEntity>> getAll();


    @Insert
    public abstract long insert(AccountEntity account);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<AccountEntity> accounts);

    @Update
    public abstract void update(AccountEntity account);

    @Delete
    public abstract void delete(AccountEntity account);

    @Query("DELETE FROM Show")
    public abstract void deleteAll();


}
