package siyafunda;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class SiyafundaApp {


    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        //return default port if heroku-port isn't set (i.e. on localhost)
        return 4567;
    }

    static Connection getDatabaseConnection(String defualtJdbcUrl) throws URISyntaxException, SQLException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String database_url = processBuilder.environment().get("DATABASE_URL");
        if (database_url != null) {

            URI uri = new URI(database_url);
            String[] hostParts = uri.getUserInfo().split(":");
            String username = hostParts[0];
            String password = hostParts[1];
            String host = uri.getHost();

            int port = uri.getPort();

            String path = uri.getPath();
            String url = String.format("jdbc:postgresql://%s:%s%s", host, port, path);

            return DriverManager.getConnection(url, username, password);

        }

        return DriverManager.getConnection(defualtJdbcUrl,"coder", "Codex2019");

    }


    public static void main(String[] args) {

        staticFiles.location("/public");
        port(getHerokuAssignedPort());

        try {
            Connection connection = getDatabaseConnection("jdbc:postgresql://localhost/funda");

            PostgresDB postDB = new PostgresDB(connection);

            get("/", (req, res) -> {
                Map dataMap = new HashMap<>();
                return new HandlebarsTemplateEngine().modelAndView(dataMap, "index.hbs");
            }, new HandlebarsTemplateEngine());

            get("/about", (req, res) -> {
                Map dataMap = new HashMap<>();
                return new HandlebarsTemplateEngine().modelAndView(dataMap, "About.hbs");
            }, new HandlebarsTemplateEngine());

            get("/contact", (req, res) -> {
                Map dataMap = new HashMap<>();
                return new HandlebarsTemplateEngine().modelAndView(dataMap, "contact.hbs");
            }, new HandlebarsTemplateEngine());

            get("/team", (req, res) -> {
                Map dataMap = new HashMap<>();
                return new HandlebarsTemplateEngine().modelAndView(dataMap, "team.hbs");
            }, new HandlebarsTemplateEngine());

            post("/level", (req, res) -> {
                Map dataMap = new HashMap<>();

                String level = req.queryParams("level");

                if (level.equalsIgnoreCase("senior")){
                    return new HandlebarsTemplateEngine().modelAndView(dataMap, "senior.hbs");
                } else if (level.equalsIgnoreCase("intermediate")){
                    return new HandlebarsTemplateEngine().modelAndView(dataMap, "intermediate.hbs");
                }

                return new HandlebarsTemplateEngine().modelAndView(dataMap, "junior.hbs");
            }, new HandlebarsTemplateEngine());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
