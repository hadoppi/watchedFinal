package com.example.watched.database.pojo;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.watched.database.entity.AccountEntity;
import com.example.watched.database.entity.ClientEntity;
import com.example.watched.database.entity.EpisodeEntity;

import java.util.List;

/**
 * https://developer.android.com/reference/android/arch/persistence/room/Relation
 */
public class TvShowWithEpisodes {
    @Embedded
    public EpisodeEntity episode;

    @Relation(parentColumn = "name", entityColumn = "TvShow", entity = EpisodeEntity.class)
    public List<AccountEntity> accounts;
}