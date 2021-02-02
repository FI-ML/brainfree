package de.brf.server.keycloak;

import lombok.Builder;
import lombok.Data;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 * @project brainfree
 */

@Data
@Builder
public class KeycloakAdminClientConfig {

    private String serverUrl;
    private String realm;
    private String clientId;
    private String clientSecret;
}
