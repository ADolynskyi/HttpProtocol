package com.goit.http;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.util.List;

public class HttpDemo {
    private static final String USER_URL ="https://jsonplaceholder.typicode.com/users";
    private static final String MODIFY_USER_URL ="https://jsonplaceholder.typicode.com/posts/1";
    private static final String POSTS_URL ="https://jsonplaceholder.typicode.com/posts";
    private static final String DELETE_USER_URL ="https://jsonplaceholder.typicode.com/posts/1";
    private static final String GET_USER_BY_ID ="https://jsonplaceholder.typicode.com/users/";


    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Task1.1________________________________________");
        User user= createDefaultUser();
        final User createdUser=HttpUtils.sendPost(URI.create(USER_URL),user);
        System.out.println("createdUser = " + createdUser);

        System.out.println("Task1.2________________________________________");
        User userTask2=createDefaultUser();
        userTask2.setName("Oleksii");
        userTask2.setId(11);
        final User modifiedUser=HttpUtils.sendPut(URI.create(MODIFY_USER_URL),userTask2);
        System.out.println("modifiedUser = " + modifiedUser);

        System.out.println("Task1.3________________________________________");
        HttpUtils.sendDelete(URI.create(DELETE_USER_URL));

        System.out.println("Task1.4________________________________________");
        List<User> users = HttpUtils.sendGetOflist(URI.create(USER_URL));
        System.out.println("users = " + users);

        System.out.println("Task1.5________________________________________");
        User userById = HttpUtils.sendGet(URI.create(String.format("%s%d", GET_USER_BY_ID, 7)));
        System.out.println("userById = " + userById);

        System.out.println("Task2________________________________________");
        getCommentsByLastPost(6);

        System.out.println("Task3________________________________________");
        getUnfinishedTasks(6);



    }
    private static void getUnfinishedTasks(int id) throws IOException, InterruptedException {
        List<Todos> todos = HttpUtils.sendGetListOfTodos(URI.create(String.format("%s/%d/todos",USER_URL,id)));
        for (Todos todo :todos){
            if(!todo.isCompleted()){
                System.out.println(todo);
            }
        }
    }
    private static void getCommentsByLastPost(int id) throws IOException, InterruptedException {
        List<Post> posts = HttpUtils.sendGetListOfPost(URI.create(String.format("%s/%d/posts",USER_URL,id)));
        int lastPostId=0;
        for(Post post: posts){
            if(post.getId()>lastPostId){
                lastPostId=post.getId();
            }
        }
        String fileName=String.format("user-%d-post-%d-comments.json",id,lastPostId);
        HttpUtils.sendGetListOfCommentsToFile(URI.create(String.format("%s/%d/comments",POSTS_URL,lastPostId)),fileName);

    }
    private static User createDefaultUser() {
        User user = new User();
        user.setId(1);
        user.setName("Anatolii");
        user.setUsername("tolik");
        user.setEmail("tolik1234@gmail.com");
            Address address =new Address();
            address.setStreet("Shevthenka");
            address.setSuite("Apt. 123");
            address.setCity("Rivne");
            address.setZipcode("15532");
                Geo geo = new Geo();
                geo.setLat("-24.2456");
                geo.setLng("92.1673");
            address.setGeo(geo);
        user.setAddress(address);
        user.setPhone("213543165465");
        user.setWebsite("website.com");
            Company company = new Company();
            company.setName("Collini");
            company.setCatchPhrase("Unternehmen Oberflaeche");
            company.setBs("Funktional baeschichtungen");
        user.setCompany(company);
        return user;

    }
}
