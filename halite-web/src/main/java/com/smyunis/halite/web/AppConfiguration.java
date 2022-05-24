package com.smyunis.halite.web;

import com.smyunis.halite.application.caterer.CatererService;
import com.smyunis.halite.application.domaineventhandlers.DomainEventDispatcher;
import com.smyunis.halite.application.domaineventhandlers.DomainEventsRegistrar;
import com.smyunis.halite.application.order.OrderService;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.order.OrderRepository;
import com.smyunis.halite.persistence.HaliteSpringJdbcAppConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

@Configuration
@ComponentScan(basePackageClasses = {AppConfiguration.class, HaliteSpringJdbcAppConfiguration.class})
public class AppConfiguration {
    @Bean
    @ApplicationScope
    DomainEventDispatcher getDomainEventDispatcher() {
        return new DomainEventDispatcher();
    }

    @Bean
    CommandLineRunner commandLineRunner(
            DomainEventDispatcher domainEventDispatcher,
            CatererRepository catererRepository,
            CateringMenuItemRepository cateringMenuItemRepository,
            OrderRepository orderRepository) {
        return args -> {
            DomainEventsRegistrar domainEventsRegistrar = new DomainEventsRegistrar(
                    domainEventDispatcher,
                    catererRepository,
                    cateringMenuItemRepository,
                    orderRepository);
            domainEventsRegistrar.assignHandlersForDomainEvents();
        };
    }

}
