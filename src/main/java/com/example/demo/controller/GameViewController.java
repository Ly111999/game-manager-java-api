package com.example.demo.controller;

import com.example.demo.entity.Game;
import com.google.gson.Gson;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class GameViewController extends HttpServlet {
    static {
        ObjectifyService.register(Game.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Game> game = ofy().load().type(Game.class).list();
        req.setAttribute("games", game);
        req.setAttribute("type", Game.Type.values());
        req.setAttribute("status", Game.Status.values());
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String iName = req.getParameter("name");
        String iPrice = req.getParameter("price");
        String iThumbnail = req.getParameter("thumbnail");
        String iDescription = req.getParameter("description");
        Game game = new Game();
        game.setName(iName);
        game.setPrice(iPrice);
        game.setThumbnail(iThumbnail);
        game.setDescription(iDescription);
        ofy().save().entities(game).now();
        resp.getWriter().print(new Gson().toJson(game));
    }
}