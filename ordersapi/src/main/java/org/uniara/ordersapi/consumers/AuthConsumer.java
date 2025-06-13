package org.uniara.ordersapi.consumers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthConsumer {
    private final String AUTH_URL = "http://usersapi:8081/api/v0/auth/validate";
    private boolean isAuthenticated;
    private Logger LOGGER = LogManager.getLogger();

    public boolean isAuthenticated(String token) {
        String response = doRequest(token);
        isAuthenticated = convertResponse(response);

        return isAuthenticated;
    }

    private String doRequest(String token) {
        HttpClient client = HttpClient.newBuilder().build();
        String response = null;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AUTH_URL))
                .header("Authorization", token)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            LOGGER.debug(e);
        }

        return response;
    }

    private boolean convertResponse(String response) {
        Boolean isValid = Boolean.parseBoolean(response);

        if (!isValid) {
            LOGGER.error("Token invalido");
        }

        return Boolean.parseBoolean(response);
    }
}
