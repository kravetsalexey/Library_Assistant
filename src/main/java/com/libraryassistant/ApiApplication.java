package com.libraryassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class ApiApplication {
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Properties properties = new Properties();
        InputStream input ;
        try {
            input = ApiApplication.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            properties.load(input);
            context = SpringApplication.run(ApiApplication.class, args);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
