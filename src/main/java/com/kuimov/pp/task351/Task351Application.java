package com.kuimov.pp.task351;

import com.kuimov.pp.task351.Communication.Communication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Task351Application {

    public static void main(String[] args) {
        SpringApplication.run(Task351Application.class, args);
        Communication communication = new Communication(new RestTemplate());
        communication.getUsers();
        communication.saveUser();
        communication.deleteUser(3L);

    }
}