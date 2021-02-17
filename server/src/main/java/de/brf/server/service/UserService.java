package de.brf.server.service;

import de.brf.server.dto.UserDto;
import de.brf.server.entity.User;
import de.brf.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public User getUserProfileOfLoggedUser(Authentication authentication) {
        User user = saveOrGetUser(authentication);
        return user;
    }

    public User findByUUID(Authentication authentication) {
        return userRepository.findByUUID(getAccessToken(authentication).getSubject());
    }

    protected User saveOrGetUser(Authentication authentication) {
        AccessToken accessToken =getAccessToken(authentication);
        Optional<User> user = Optional.of(userRepository.findByUUID(accessToken.getSubject()));

        if(user.isPresent()) {
            return user.get();
        }else{
            user.get().setFirstName(accessToken.getGivenName());
            user.get().setLastName(accessToken.getFamilyName());
            user.get().setEmail(accessToken.getEmail());
            return userRepository.save(user.get());
        }
    }

    private AccessToken getAccessToken(Authentication authentication){
        return ((KeycloakPrincipal) authentication.getPrincipal())
                .getKeycloakSecurityContext()
                .getToken();
    }

    public UserDto userToDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
