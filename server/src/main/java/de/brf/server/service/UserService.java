package de.brf.server.service;

import de.brf.server.dto.UserDto;
import de.brf.server.entity.User;
import de.brf.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author maximilian lamm brain.free.kontakt@gmail.com
 * @project brainfree
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto getUserProfileOfLoggedUser(Authentication authentication) {
        User user = saveOrGetUser(authentication);
        return userEntityToDto(user);
    }

    public UserDto getUserByKeycloakId(String keycloakId) {
        User user = userRepository.findByKeycloakId(keycloakId);
        return userEntityToDto(user);
    }

    protected User saveOrGetUser(Authentication authentication) {

        AccessToken accessToken = getAccessToken(authentication);

        if (!userExists(accessToken)) {
            User user = new User();
            user.setFirstName(accessToken.getGivenName());
            user.setLastName(accessToken.getFamilyName());
            user.setEmail(accessToken.getEmail());
            user.setKeycloakId(accessToken.getSubject());
            return userRepository.save(user);
        } else {
            return userRepository.findByKeycloakId(accessToken.getSubject());
        }
    }

    private AccessToken getAccessToken(Authentication authentication) {
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

    private boolean userExists(AccessToken accessToken) {
        return userRepository.findByKeycloakId(accessToken.getSubject()) == null;
    }
}
