import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "keycloak-angular";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  email: string | undefined;

  fullName: string | undefined;

  constructor(private userService: UserService, private keycloakService: KeycloakService) {
  }

  ngOnInit(): void {
    this.getUser();
  }

  getUser() {
    this.userService.getUser().subscribe(
      user => {
        this.fullName =  user.firstName + " " +  user.lastName;
        this.email = user.email;
      }
    )
  }

  openUserKeycloakAccount(){
    location.href = "http://localhost:8070/auth/realms/brainfree/account/"
  }

  logout() {
    this.keycloakService.logout();
  }

}

