package com.goit.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtils {
     private static final HttpClient CLIENT=HttpClient.newHttpClient();
     private static final Gson GSON = new Gson();
     public static User sendGet(URI uri) throws IOException, InterruptedException {
          HttpRequest request = HttpRequest.newBuilder()
                  .uri(uri)
                  .GET()
                  .build();
          HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
          User user = GSON.fromJson(response.body(), User.class);
          return user;
     }
     public static User sendPost(URI uri, User user) throws IOException, InterruptedException {
          String requestBody = GSON.toJson(user);
          HttpRequest request = HttpRequest.newBuilder()
                  .header("Content-type","application/json")
                  .uri(uri)
                  .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                  .build();
          HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
          return GSON.fromJson(response.body(),User.class);
     }

     public static User sendPut(URI uri, User user) throws IOException, InterruptedException {
          String requestBody = GSON.toJson(user);
          HttpRequest request = HttpRequest.newBuilder()
                  .header("Content-type","application/json")
                  .uri(uri)
                  .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                  .build();
          HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
          System.out.println("response.statusCode() = " + response.statusCode());
          return GSON.fromJson(response.body(),User.class);
     }
}
