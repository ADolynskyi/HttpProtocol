package com.goit.http;

import java.io.IOException;
import java.net.URI;

public class HttpDemo {
    private static final String USER_URL ="https://jsonplaceholder.typicode.com/users";
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Task1");
        User user= createDefaultUser();
        final User createdUser=HttpUtils.sendPost(URI.create(USER_URL),user);
        System.out.println("createdUser = " + createdUser);
        System.out.println("Task2");
        User userTask2=createDefaultUser();
        userTask2.setName("Oleksii");
        userTask2.setId(11);
        final User modifiedUser=HttpUtils.sendPut(URI.create(USER_URL),userTask2);
        System.out.println("modifiedUser = " + modifiedUser);

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
