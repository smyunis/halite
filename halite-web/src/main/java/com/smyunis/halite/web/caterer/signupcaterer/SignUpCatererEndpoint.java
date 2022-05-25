package com.smyunis.halite.web.caterer.signupcaterer;

import com.smyunis.halite.application.users.User;
import com.smyunis.halite.application.users.UserRole;
import com.smyunis.halite.application.users.UserService;
import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.web.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/caterers")
public class SignUpCatererEndpoint {
    private final UserService userService;
    private final Mapper<SignUpCatererRequestPayload, User> userMapper;
    private final Mapper<SignUpCatererRequestPayload, CatererData> catererDataMapper;

    @Autowired
    public SignUpCatererEndpoint(UserService userService,
                                 Mapper<SignUpCatererRequestPayload, User> userMapper,
                                 Mapper<SignUpCatererRequestPayload, CatererData> catererDataMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.catererDataMapper = catererDataMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> signUpCaterer(@RequestBody SignUpCatererRequestPayload payload) {
        User user = getUser(payload);
        CatererData catererData = catererDataMapper.map(payload);
        userService.signUpUserAsCaterer(user, catererData);

        URI createdResourceUri = getCreatedResourceLocation(catererData.getId().toString());
        return ResponseEntity.created(createdResourceUri).build();
    }

    private User getUser(SignUpCatererRequestPayload payload) {
        User user = userMapper.map(payload);
        user.setRole(UserRole.CATERER);
        return user;
    }

    private URI getCreatedResourceLocation(String catererId) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(catererId)
                .toUri();
    }

}
