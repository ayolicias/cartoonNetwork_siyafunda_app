package siyafunda;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class SiyafundaApp {

    public static void main(String[] args) {
        staticFiles.location("/public");
        port(8000);

        get("/", (req, res)-> {
            Map dataMap = new HashMap<>();
            return new HandlebarsTemplateEngine().modelAndView(dataMap, "home.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
