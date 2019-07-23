package com.example.demo.taskqueue;

import com.example.demo.entity.Article;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskHandle;
import com.googlecode.objectify.ObjectifyService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import static com.googlecode.objectify.ObjectifyService.ofy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GetQueue extends HttpServlet {
    static {
        ObjectifyService.register(Article.class);
    }

    private static Queue q = QueueFactory.getQueue("queue-green");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TaskHandle> tasks = q.leaseTasks(30, TimeUnit.SECONDS, 1);
        if (tasks.size() > 0){
            TaskHandle task = tasks.get(0);
            String link = new String(task.getPayload());
            Document document = Jsoup.connect(link).ignoreContentType(true).get();
            String title = document.select(".title_news").text();
            String description = document.select(".short_intro").text();
            String content = document.select(".fck_detail").text();
            String author = document.select(".author_mail strong").text();
            System.out.println(title);
            System.out.println(description);
            System.out.println(content);
            System.out.println(author);
            Article article = new Article(link);
            article.setTitle(title);
            article.setDescription(description);
            article.setContent(content);
            article.setAuthor(author);
            ofy().save().entity(article).now();
            q.deleteTask(task);
        }
    }
}
