package com.smyunis.halite.web;

import com.smyunis.halite.application.caterer.CatererService;
import com.smyunis.halite.application.domaineventhandlers.DomainEventDispatcher;
import com.smyunis.halite.application.order.OrderService;
import com.smyunis.halite.application.users.UserRepository;
import com.smyunis.halite.application.users.UserService;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.cateringeventhost.CateringEventHostRepository;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.order.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationServiceBeansConfiguration {
    @Bean
    CatererService getCatererService(CatererRepository catererRepository) {
        return new CatererService(catererRepository);
    }

    @Bean
    OrderService getOrderService(
            DomainEventDispatcher eventDispatcher,
            OrderRepository orderRepository,
            CateringMenuItemRepository cateringMenuItemRepository) {
        return new OrderService(eventDispatcher, orderRepository, cateringMenuItemRepository);
    }

    @Bean
    UserService getUserService(UserRepository userRepository,
                               CatererRepository catererRepository,
                               CateringEventHostRepository cateringEventHostRepository) {
        return new UserService(userRepository, catererRepository, cateringEventHostRepository);
    }
}
