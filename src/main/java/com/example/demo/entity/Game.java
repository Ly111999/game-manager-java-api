package com.example.demo.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.Calendar;

@Entity
public class Game {
    @Id
    private long id;
    @Index
    private String name;
    private long release_date;
    private String price;
    private String thumbnail;
    private String description;
    private int type;
    @Index
    private int status;

    public Game() {
        this.id = Calendar.getInstance().getTimeInMillis();
        this.release_date = Calendar.getInstance().getTimeInMillis();
    }

    //enum type
    @Index
    public enum Type {
        ACTION(1), FPS(2);

        int value;

        Type(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Game.Type findByValue(int value) {
            for (Game.Type type : Game.Type.values()) {
                if (type.getValue() == value) {
                    return type;
                }
            }
            return null;
        }
    }

    //geter and seter of type
    public int getType() {
        return type;
    }

    public void setType(Game.Type type) {
        if (type == null) {
            type = Game.Type.ACTION;
        }
        this.type = type.getValue();
    }

    //enum status
    @Index
    public enum Status {
        ACTIVE(1), DEACTIVE(0), DELETED(-1);

        int value;

        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Game.Status findByValue(int value) {
            for (Game.Status status :
                    Game.Status.values()) {
                if (status.getValue() == value) {
                    return status;
                }
            }
            return null;
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Game.Status status) {
        if (status == null) {
            status = Game.Status.DEACTIVE;
        }
        this.status = status.getValue();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRelease_date() {
        return release_date;
    }

    public void setRelease_date(long release_date) {
        this.release_date = release_date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
