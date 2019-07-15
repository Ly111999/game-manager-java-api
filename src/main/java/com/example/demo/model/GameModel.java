package com.example.demo.model;

import com.example.demo.entity.Game;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class GameModel {
    public boolean addGame(Game game) {
        ofy().save().entities(game).now();
        return true;
    }

    public List<Game> listGame() {
        List<Game> listGame = ofy().load().type(Game.class).filter("status != ", -1).list();
        return listGame;
    }

    public boolean updateGame(Game game) {
        ofy().defer().save().entity(game);
        return true;
    }

    public boolean deleteGame(Game game) {
        ofy().defer().save().entity(game);
        return true;
    }

    public Game getProductById(long id) {
        Game game = ofy().load().type(Game.class).id(id).now();
        return game;
    }

    public Game getProductByName(String name) {
        Game game = ofy().load().type(Game.class).filter("name", name).first().now();
        return game;
    }
}
