package com.kuimov.pp.task351.Communication;

import com.kuimov.pp.task351.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static java.lang.String.join;

@Component
public class Communication {

    static final String URL = "http://94.198.50.185:7081/api/users";
    static private List<String> cookie;
    final RestTemplate restTemplate;
    HttpHeaders headers = new HttpHeaders();

    @Autowired
    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getUsers() {
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        // Request to return JSON format
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        // HttpEntity<List<User>>: To get result as List<User>.
//        HttpEntity<List<User>> entity = new HttpEntity<List<User>>(headers);
//        // Send request with GET method, and Headers.
        ResponseEntity<List<User>> response = restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
        HttpStatus statusCode = response.getStatusCode();

        cookie = response.getHeaders().get("Set-Cookie");
        //headers.set("Cookie", join(";", cookie)); // в заголовок добавил куки

        System.out.println("Response Satus Code: " + statusCode);
        System.out.println("Response cookies: " + cookie);

        List<User> list = response.getBody();

        for (User e : list) {
            System.out.println(e.toString());
        }
        return list;
    }

    public void saveUser() {
        User user = new User();
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 33);
        //headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", join(";", cookie)); // в заголовок добавил куки
        HttpEntity<User> entity = new HttpEntity<User>(user, headers);
        String body = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class, user).getBody();
        System.out.println(body);
        System.out.println(cookie);

        System.out.println("_________________________");

        user.setName("Thomas");
        user.setLastName("Shelby");
        entity = new HttpEntity<User>(user, headers);
        String updateBody = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class, user).getBody();
        System.out.println(updateBody);
        System.out.println(cookie);
        System.out.println("_________________________");

    }

    public void deleteUser(long id) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", join(";", cookie)); // в заголовок добавил куки
        HttpEntity<String> entity = new HttpEntity<String>("body", headers);
        String deleteBody = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class).getBody();
        System.out.println(deleteBody);
    }
}

