package com.pluralsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.pluralsight.application.DeliApp;

@SpringBootApplication
public class Main
{
    public static void main(String[] args)
    {
        DeliApp app = new DeliApp();
        app.run();
        SpringApplication.run(Main.class, args);
    }
}