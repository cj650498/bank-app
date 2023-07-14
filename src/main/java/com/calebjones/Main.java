package com.calebjones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.calebjones.database.DBConnectionManager;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        // For testing
		DBConnectionManager.clearDatabase();

        SpringApplication.run(Main.class, args);
    }

}
