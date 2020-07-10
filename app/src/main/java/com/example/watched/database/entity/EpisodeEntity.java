package com.example.watched.database.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Episodes",
        foreignKeys =
        @ForeignKey(
                entity = TvShowEntity.class,
                parentColumns = "name",
                childColumns = "TvShow",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {
        @Index(
                value = {"name"}
        )}
)
public class EpisodeEntity {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String name;
    private int numberEpisode;
    private String TvShow;
    private int time;
    private String synopsis;

    public EpisodeEntity() {
    }

    public EpisodeEntity(String TvShow, String name, int numberEpisode, int time, String synopsis) {
        this.TvShow = TvShow;
        this.name = name;
        this.numberEpisode = numberEpisode;
        this.time = time;
        this.synopsis = synopsis;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberEpisode() {
        return numberEpisode;
    }

    public void setNumberEpisode(int numberEpisode) {
        this.numberEpisode = numberEpisode;
    }

    public String getTvShow() {
        return TvShow;
    }

    public void setTvShow(String tvShow) {
        TvShow = tvShow;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof EpisodeEntity)) return false;
        EpisodeEntity o = (EpisodeEntity) obj;
        return o.getId().equals(this.getId());
    }

    @Override
    public String toString() {
        return name;
    }
}
