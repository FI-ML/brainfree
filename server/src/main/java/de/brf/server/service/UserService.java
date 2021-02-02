package de.brf.server.service;

import de.brf.server.dto.UserDto;
import de.brf.server.entity.User;
import de.brf.server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */

@Service
@AllArgsConstructor
public class UserService {


    private UserRepository userRepository;

    public UserDto getUserProfileOfLoggedUser(Authentication authentication) {
        User user = saveUser(authentication);
        return userEntityToDto(user);
    }

    public UserDto getUserByKeycloakId(String keycloakId) {
        User user = userRepository.findByKeycloakId(keycloakId);
        return userEntityToDto(user);
    }

    protected User saveUser(Authentication authentication) {
        User user = new User();

        AccessToken accessToken = getAccessToken(authentication);


        if(! isUserExists(accessToken)) {
            return userRepository.findByKeycloakId(accessToken.getSubject());
        }else{
            user.setFirstName(accessToken.getGivenName());
            user.setLastName(accessToken.getFamilyName());
            user.setEmail(accessToken.getEmail());
            user.setKeycloakId(accessToken.getSubject());
            return userRepository.save(user);
        }
    }

    private AccessToken getAccessToken(Authentication authentication){
        return ((KeycloakPrincipal) authentication.getPrincipal())
                .getKeycloakSecurityContext()
                .getToken();
    }

    private UserDto userEntityToDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .keycloakId(user.getKeycloakId())
                .build();
    }

    private boolean isUserExists(AccessToken accessToken) {
        return  userRepository.findByKeycloakId(accessToken.getSubject()) == null;
    }
}
