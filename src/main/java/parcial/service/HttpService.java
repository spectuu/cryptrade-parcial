package parcial.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import parcial.model.Cryptocoin;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class HttpService {

    public static List<Cryptocoin> get(String url) {

        try {

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .header("Accept", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

            Cryptocoin[] cryptocoinsArray = gson.fromJson(jsonResponse.getAsJsonArray("data"), Cryptocoin[].class);

            List<Cryptocoin> cryptocoins = Arrays.asList(cryptocoinsArray);

            cryptocoins.forEach(System.out::println);

            return cryptocoins;

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return null;

    }
}
