package com.smyunis.halite.web;

import com.smyunis.halite.application.caterer.CatererService;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.persistence.HaliteSpringJdbcAppConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {AppConfiguration.class, HaliteSpringJdbcAppConfiguration.class})
public class AppConfiguration {

    @Bean
    CatererService getCatererService(CatererRepository catererRepository) {
        return new CatererService(catererRepository);
    }
}
