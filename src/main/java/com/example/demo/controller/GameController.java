package com.example.demo.controller;

import com.example.demo.entity.Game;
import com.example.demo.model.GameModel;
import com.example.demo.util.JsonApiObject;
import com.google.gson.Gson;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class GameController extends HttpServlet {
    static {
        ObjectifyService.register(Game.class);
    }

    GameModel model = new GameModel();
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setHeader("Cache-Control", "nocache");
        resp.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        if (id != null && id.length() > 0) {
            Game game = model.getProductById(Long.parseLong(id));
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().print(gson.toJson(game));
        } else {
            List<Game> list = model.listGame();
            JsonApiObject jsonApiObject = new JsonApiObject(
                    "OK",
                    HttpServletResponse.SC_OK,
                    list
            );
            resp.getWriter().print(gson.toJson(jsonApiObject));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setHeader("Cache-Control", "nocache");
        resp.setCharacterEncoding("utf-8");
        BufferedReader reader = req.getReader();
        Game game = gson.fromJson(reader, Game.class);
        if (checkExist(game) == true) {
            Game gameSave = new Game();
            gameSave.setName(game.getName());
            gameSave.setThumbnail(game.getThumbnail());
            gameSave.setPrice(game.getPrice());
            gameSave.setDescription(game.getDescription());
            gameSave.setType(Game.Type.ACTION);
            gameSave.setStatus(Game.Status.ACTIVE);
            boolean check = model.addGame(gameSave);
            if (check == true) {
                JsonApiObject jsonApiObject = new JsonApiObject(
                        "Success",
                        HttpServletResponse.SC_OK
                );
                resp.getWriter().print(gson.toJson(jsonApiObject));
            } else {
                JsonApiObject jsonApiObject = new JsonApiObject(
                        "Add game Failed",
                        HttpServletResponse.SC_BAD_REQUEST
                );
                resp.getWriter().print(gson.toJson(jsonApiObject));
            }
        } else {
            JsonApiObject jsonApiObject = new JsonApiObject(
                    "This game has been exist",
                    HttpServletResponse.SC_BAD_REQUEST
            );
            resp.getWriter().print(gson.toJson(jsonApiObject));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setHeader("Cache-Control", "nocache");
        resp.setCharacterEncoding("utf-8");
        Game game = gson.fromJson(req.getReader(), Game.class);
        String id = req.getParameter("id");
        int status = game.getStatus();
        if (id != null && id.length() > 0 && status != -1) {
            Game gameUpdate = model.getProductById(Long.parseLong(id));
            if (gameUpdate != null) {
                gameUpdate.setPrice(game.getPrice());
                gameUpdate.setThumbnail(game.getThumbnail());
                gameUpdate.setName(game.getName());
                gameUpdate.setDescription(game.getDescription());
                boolean check = model.updateGame(gameUpdate);
                if (check == true) {
                    JsonApiObject jsonApiObject = new JsonApiObject(
                            "Update success",
                            HttpServletResponse.SC_OK,
                            gameUpdate
                    );
                    resp.getWriter().print(gson.toJson(jsonApiObject));
                } else {
                    JsonApiObject jsonApiObject = new JsonApiObject(
                            "Update failed",
                            HttpServletResponse.SC_BAD_REQUEST
                    );
                    resp.getWriter().print(gson.toJson(jsonApiObject));
                }
            } else {
                JsonApiObject jsonApiObject = new JsonApiObject(
                        "Not Found",
                        HttpServletResponse.SC_NOT_FOUND
                );
                resp.getWriter().print(gson.toJson(jsonApiObject));
            }
        } else {
            JsonApiObject jsonApiObject = new JsonApiObject(
                    "Not Found",
                    HttpServletResponse.SC_NOT_FOUND
            );
            resp.getWriter().print(gson.toJson(jsonApiObject));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setHeader("Cache-Control", "nocache");
        resp.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        if (id != null && id.length() > 0) {
            Game game = model.getProductById(Long.parseLong(id));
            int status = game.getStatus();
            if (game != null && status != -1) {
                game.setStatus(Game.Status.DELETED);
                boolean check = model.deleteGame(game);
                if (check == true) {
                    JsonApiObject jsonApiObject = new JsonApiObject(
                            "Delete Success",
                            HttpServletResponse.SC_OK
                    );
                    resp.getWriter().print(gson.toJson(jsonApiObject));
                } else {
                    JsonApiObject jsonApiObject = new JsonApiObject(
                            "Delete failed",
                            HttpServletResponse.SC_BAD_REQUEST
                    );
                    resp.getWriter().print(gson.toJson(jsonApiObject));
                }
            } else {
                JsonApiObject jsonApiObject = new JsonApiObject(
                        "Not Found or deleteds",
                        HttpServletResponse.SC_NOT_FOUND
                );
                resp.getWriter().print(gson.toJson(jsonApiObject));
            }
        } else {
            JsonApiObject jsonApiObject = new JsonApiObject(
                    "Not Found",
                    HttpServletResponse.SC_NOT_FOUND
            );
            resp.getWriter().print(gson.toJson(jsonApiObject));
        }
    }

    private boolean checkExist(Game game) {
        Game game1 = model.getProductByName(game.getName());
        if (game1 == null) {
            return true;
        } else {
            return false;
        }
    }
}
