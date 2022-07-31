package com.mcardoso.biblioteca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mcardoso.biblioteca.service.DBService;

@Configuration
@Profile("test")
public class TestConfig {
    
    @Autowired
    private DBService dbService;

    @Bean
    //@Resource
    public void instanciaBD() {
        this.dbService.instanciaBD();
    }
}
