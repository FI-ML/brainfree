import {KeycloakOptions} from 'keycloak-angular/lib/core/interfaces/keycloak-options';
import {KeycloakConfig, KeycloakInitOptions} from "keycloak-js";

const keycloakConfig: KeycloakConfig = {
  url: 'http://localhost:8070/auth/',
  realm: 'brainfree',
  clientId: 'app-frontend',

};

const keycloakInitOptions: KeycloakInitOptions = {
  onLoad: 'login-required',
  checkLoginIframe: false
};

const keycloakOptions: KeycloakOptions = {
  config: keycloakConfig,
  initOptions: keycloakInitOptions,
  enableBearerInterceptor: true
};

export const environment = {
  production: false,
  keycloakOptions
};
