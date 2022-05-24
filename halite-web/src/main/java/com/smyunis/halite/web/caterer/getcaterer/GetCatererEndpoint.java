package com.smyunis.halite.web.caterer.getcaterer;

import com.smyunis.halite.application.caterer.CatererService;
import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererDataReadOnlyProxy;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.web.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/caterers")
public class GetCatererEndpoint {

    private final CatererService catererService;
    private final Mapper<CatererDataReadOnlyProxy, GetCatererResponsePayload> mapper;

    @Autowired
    public GetCatererEndpoint(CatererService catererService,
                              Mapper<CatererDataReadOnlyProxy, GetCatererResponsePayload> mapper) {
        this.catererService = catererService;
        this.mapper = mapper;
    }

    @GetMapping("{catererId}")
    public GetCatererResponsePayload getCaterer(@PathVariable String catererId) {
        Caterer caterer = catererService.getCaterer(new CatererId(catererId));
        return mapper.map(caterer.getDataReadOnlyProxy());
    }
}
