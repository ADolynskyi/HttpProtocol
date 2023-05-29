package com.goit.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
          System.out.println("response.statusCode() = " + response.statusCode());
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
     public static void sendDelete(URI uri) throws IOException, InterruptedException {
          HttpRequest request = HttpRequest.newBuilder()
                  .header("Content-type","application/json")
                  .uri(uri)
                  .DELETE()
                  .build();
          HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
          System.out.println("response.statusCode() = " + response.statusCode());
     }
     public static List<User> sendGetOflist(URI uri) throws IOException, InterruptedException {
          HttpRequest request=HttpRequest.newBuilder()
                  .uri(uri)
                  .GET()
                  .build();
          HttpResponse<String> response =CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
          return GSON.fromJson(response.body(),new TypeToken<List<User>>(){}.getType());
     }
     public static List<Post> sendGetListOfPost(URI uri) throws IOException, InterruptedException {
          HttpRequest request= HttpRequest.newBuilder()
                  .uri(uri)
                  .GET()
                  .build();
          HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
          System.out.println("response.statusCode() = " + response.statusCode());
          return GSON.fromJson(response.body(),new TypeToken<List<Post>>(){}.getType());
     }
     public static void sendGetListOfCommentsToFile(URI uri, String fileName) throws IOException, InterruptedException {
          HttpRequest request = HttpRequest.newBuilder()
                  .uri(uri)
                  .GET()
                  .build();
         HttpResponse response= CLIENT.send(request,HttpResponse.BodyHandlers.ofFile(Paths.get(fileName)));
          System.out.println("response.statusCode() = " + response.statusCode());
     }
     public static List<Todos> sendGetListOfTodos(URI uri) throws IOException, InterruptedException {
          HttpRequest request = HttpRequest.newBuilder()
                  .uri(uri)
                  .GET()
                  .build();
          HttpResponse<String> response=CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
          System.out.println("response.statusCode() = " + response.statusCode());
          return GSON.fromJson(response.body(),new TypeToken<List<Todos>>(){}.getType());

     }
}
