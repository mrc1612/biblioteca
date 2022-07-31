package com.mcardoso.biblioteca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mcardoso.biblioteca.service.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String estrategia;

    @Bean
    //@Resource
    public boolean instanciaBD() {
        if (estrategia.equals("create")) {
            this.dbService.instanciaBD();
        }
        return false;
    }
}
