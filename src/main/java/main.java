/**
 * Created by domin_000 on 14.11.2015.
 */
import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        staticFileLocation("public");
        webSocket("/chat", WebSocketHandler.class);
        init();
    }
}
