package siyafunda;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class SiyafundaApp {

    public static void main(String[] args) {
        staticFiles.location("/public");
        port(8000);

<<<<<<< HEAD
        try {
            Connection connection =  DriverManager.getConnection("jdbc:postgresql://localhost/funda");

            get("/", (req, res) -> {
                Map dataMap = new HashMap<>();
                return new HandlebarsTemplateEngine().modelAndView(dataMap, "home.hbs");
            }, new HandlebarsTemplateEngine());
        }catch (Exception e) {
            e.printStackTrace();
        }
=======
        get("/", (req, res)-> {
            Map dataMap = new HashMap<>();
            return new HandlebarsTemplateEngine().modelAndView(dataMap, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (req, res)-> {
            Map dataMap = new HashMap<>();
            return new HandlebarsTemplateEngine().modelAndView(dataMap, "About.hbs");
        }, new HandlebarsTemplateEngine());
    }
>>>>>>> ac160de99a575fbfecf03d5d1cb2bb29ef41c389
    }




