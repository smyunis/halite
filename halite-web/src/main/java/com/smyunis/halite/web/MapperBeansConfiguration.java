package com.smyunis.halite.web;

import com.smyunis.halite.application.users.User;
import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.domain.caterer.CatererDataReadOnlyProxy;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemData;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.domain.order.OrderDataReadOnlyProxy;
import com.smyunis.halite.web.caterer.addcateringmenuitem.AddMenuItemRequestPayload;
import com.smyunis.halite.web.caterer.addcateringmenuitem.AddMenuItemRequestPayloadToMenuItemDataMapper;
import com.smyunis.halite.web.caterer.getcaterer.GetCatererResponsePayload;
import com.smyunis.halite.web.caterer.getcaterer.GetCatererResponsePayloadMapper;
import com.smyunis.halite.web.caterer.signupcaterer.SignUpCatererRequestPayload;
import com.smyunis.halite.web.caterer.signupcaterer.SignUpCatererRequestPayloadToCatererDataMapper;
import com.smyunis.halite.web.caterer.signupcaterer.SignUpCatererRequestPayloadToUserMapper;
import com.smyunis.halite.web.order.createorder.CreateOrderRequestPayload;
import com.smyunis.halite.web.order.createorder.CreateOrderRequestPayloadMapper;
import com.smyunis.halite.web.order.getorder.GetOrderResponsePayload;
import com.smyunis.halite.web.order.getorder.OrderToGetOrderResponsePayloadMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperBeansConfiguration {
    @Bean
    Mapper<OrderDataReadOnlyProxy, GetOrderResponsePayload> getOrderResponsePayloadMapper() {
        return new OrderToGetOrderResponsePayloadMapper();
    }

    @Bean
    Mapper<CatererDataReadOnlyProxy, GetCatererResponsePayload> getCatererResponsePayloadMapper() {
        return new GetCatererResponsePayloadMapper();
    }

    @Bean
    Mapper<CreateOrderRequestPayload, OrderData> getCreateOrderRequestPayloadMapper() {
        return new CreateOrderRequestPayloadMapper();
    }

    @Bean
    Mapper<SignUpCatererRequestPayload, CatererData> getSignUpCatererRequestPayloadToCatererDataMapper() {
        return new SignUpCatererRequestPayloadToCatererDataMapper();
    }

    @Bean
    Mapper<SignUpCatererRequestPayload, User> getSignUpCatererRequestPayloadToUserMapper() {
        return new SignUpCatererRequestPayloadToUserMapper();
    }

    @Bean
    Mapper<AddMenuItemRequestPayload, CateringMenuItemData> getAddMenuItemRequestPayloadToMenuItemMapper() {
        return new AddMenuItemRequestPayloadToMenuItemDataMapper();
    }
}

