package com.devko.magnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableJpaAuditing
public class MagnetApplication {

    public static final String CONFIG_LOCATIONS = "spring.config.location=" + "classpath:application.yml, " + "classpath:aws.yml";

    public static void main(String[] args) {

        new SpringApplicationBuilder(MagnetApplication.class)
                .properties(CONFIG_LOCATIONS)
                .run(args);

    }

}
