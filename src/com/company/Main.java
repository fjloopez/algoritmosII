import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        org.apache.log4j.BasicConfigurator.configure();

        port(8080);

        staticFileLocation("/public");

        // Tramos y Lugares

        get("/api/lugares", (request, response) -> {
            return "[{\"id\": 1, \"nombre\": \"Buenos Aires (EZE)\"}, {\"id\": 2, \"nombre\": \"Buenos Aires (AEP)\"}, {\"id\": 3, \"nombre\": \"Florianopolis (FLP)\"}]";
        });

        get("/api/tramos", (request, response) -> {
            return "[{\"id\": 1, \"origen\": 1, \"destino\": 3, \"costo\": 500, \"ganancia\": 200}, {\"id\": 2, \"origen\": 2, \"destino\": 3, \"costo\": 450, \"ganancia\": 210}, {\"id\": 3, \"origen\": 3, \"destino\": 1, \"costo\": 500, \"ganancia\": 200}]";
        });

        get("/api/tramos/:id", (request, response) -> {
            switch (request.params(":id")) {
                case "1": return "{\"id\": 1, \"origen\": 1, \"destino\": 3, \"costo\": 500, \"ganancia\": 200}";
                case "2": return "{\"id\": 2, \"origen\": 2, \"destino\": 3, \"costo\": 450, \"ganancia\": 210}";
                case "3": return "{\"id\": 3, \"origen\": 3, \"destino\": 1, \"costo\": 500, \"ganancia\": 200}";
                default: halt(404); return "No existe";
            }
        });

        put("/api/tramos", (request, response) -> {
            return "OK";
        });

        patch("/api/tramos/:id", (request, respnse) -> {
            return "OK" + request.params(":id");
        });

        delete("/api/tramos/:id", (request, respnse) -> {
            return "OK" + request.params(":id");
        });

    }
}
