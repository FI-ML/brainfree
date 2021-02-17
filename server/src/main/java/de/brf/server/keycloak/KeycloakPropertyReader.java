package de.brf.server.keycloak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 * @project brainfree
 */

@Configuration
@PropertySource("classpath:application.yml")
public class KeycloakPropertyReader {

    @Autowired
    private Environment env;

    public String getProperty(String key) {
        return env.getProperty(key);
    }

}
