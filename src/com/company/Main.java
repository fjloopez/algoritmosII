package com.company;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        org.apache.log4j.BasicConfigurator.configure();

        port(8080);

        staticFileLocation("../../../../web");

        get("/api/lugares", (request, response) -> {
            return "[{\"id\": 1, \"nombre\": \"Buenos Aires (EZE)\"}, {\"id\": 2, \"nombre\": \"Buenos Aires (AEP)\"}, {\"id\": 3, \"nombre\": \"Florianopolis (FLP)\"}]";
        });

        get("/api/tramos", (request, response) -> {
            return "[{\"id\": 1, \"origen\": 1, \"destino\": 3, \"costo\": 500, \"ganancia\": 200}, {\"id\": 2, \"origen\": 2, \"destino\": 3, \"costo\": 450, \"ganancia\": 210}, {\"id\": 3, \"origen\": 3, \"destino\": 1, \"costo\": 500, \"ganancia\": 200}]";
        });


    }
}
