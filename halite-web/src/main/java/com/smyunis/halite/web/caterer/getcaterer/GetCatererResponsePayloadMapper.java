package com.smyunis.halite.web.caterer.getcaterer;

import com.smyunis.halite.domain.caterer.CatererDataReadOnlyProxy;
import com.smyunis.halite.web.Mapper;

public class GetCatererResponsePayloadMapper implements Mapper<CatererDataReadOnlyProxy,GetCatererResponsePayload> {
    @Override
    public GetCatererResponsePayload map(CatererDataReadOnlyProxy obj) {
        return new GetCatererResponsePayload()
                .setId(obj.getId().getIdString())
                .setName(obj.getName())
                .setPersonalDescription(obj.getPersonalDescription())
                .setPhoneNumber(obj.getPhoneNumber().phoneNumber())
                .setCatererImage(obj.getCatererImage().toString());
    }
}
