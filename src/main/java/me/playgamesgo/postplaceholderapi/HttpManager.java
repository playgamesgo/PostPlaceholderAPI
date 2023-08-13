package me.playgamesgo.postplaceholderapi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.PluginLogger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class HttpManager {
    public static HttpServer server = null;
    public HttpManager(PluginLogger logger) {
        try {
            server = HttpServer.create(new InetSocketAddress(PostPlaceholderAPI.config.getInt("port")), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        server.createContext("/", new EmptyHandler());
        server.setExecutor(null);
        server.start();
        logger.log(Level.INFO, "Started HTTP server on " + server.getAddress().toString());
    }

    static class EmptyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            List<String> tokens = PostPlaceholderAPI.config.getStringList("tokens");
            if (!t.getRequestHeaders().containsKey("Token") || !tokens.contains(t.getRequestHeaders().get("Token").get(0))) {
                String response = "Unauthorized";
                t.sendResponseHeaders(401, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
                return;
            }
            String token = t.getRequestHeaders().get("Token").get(0);

            String uri = t.getRequestURI().toString();
            if (uri.length() < 39) {
                String response = "Invalid URI";
                t.sendResponseHeaders(404, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
                return;
            }
            String uuidString = uri.substring(1, 37);
            UUID uuid = UUID.fromString(uuidString);
            String placeholder = uri.substring(38);
            OfflinePlayer player = PostPlaceholderAPI.server.getOfflinePlayer(uuid);

            String response = PlaceholderAPI.setPlaceholders(player, "%" + placeholder + "%");
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
