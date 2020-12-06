package com.example.foreignkeyexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForeignkeyexampleApplication {

    public static void main(String[] args) {

        var context = SpringApplication.run(ForeignkeyexampleApplication.class, args);
        var runner = context.getBeanFactory().getBean(StreamRunner.class);
        runner.Run();
    }

}
