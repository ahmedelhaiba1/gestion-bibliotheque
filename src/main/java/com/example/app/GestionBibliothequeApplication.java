package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.context.annotation.Bean;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;

@SpringBootApplication
public class GestionBibliothequeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionBibliothequeApplication.class, args);
    }

    @Bean
    public InstrumentationLoadTimeWeaver loadTimeWeaver() {
        return new InstrumentationLoadTimeWeaver();
    }
}

