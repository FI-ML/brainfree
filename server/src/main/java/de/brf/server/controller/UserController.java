package de.brf.server.controller;

import de.brf.server.dto.UserDto;
import de.brf.server.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/brainfree/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "user")
    public UserDto profileOfCurrentUser(Authentication authentication) {
        return userService.getUserProfileOfLoggedUser(authentication);
    }
}
