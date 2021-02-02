import {BrowserModule} from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {MatToolbarModule} from "@angular/material/toolbar";
import {InfoComponent} from './components/info/info.component';
import {HeaderComponent} from "./components/header/header.component";
import {FooterComponent} from './components/footer/footer.component';
import {FormsModule} from "@angular/forms";
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {environment} from "../environments/environment";

import {MatMenuModule} from "@angular/material/menu";
import {HttpErrorInterceptor} from "./interceptor/HttpErrorInterceptor";


export function keyclaokInitializer(keycloak: KeycloakService): () => Promise<any> {
  return (): Promise<any> => {
    return new Promise(async (resolve, reject) => {
      try {
        await keycloak.init(environment.keycloakOptions);
        console.log('Keycloak is initialized');
        resolve();
      } catch (error) {
        console.log('Error thrown in init ' + error);
        reject(error);
      }
    });
  };
}


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    InfoComponent,
    FooterComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserAnimationsModule,
    BrowserModule,
    HttpClientModule,
    KeycloakAngularModule,
    MatToolbarModule,
    MatMenuModule,
    FormsModule,

  ],
  providers: [
    {provide: APP_INITIALIZER, useFactory: keyclaokInitializer, multi: true, deps: [KeycloakService]},
    {provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
