import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})

export class UserService {

  private url = 'http://localhost:8080/brainfree/user';

  constructor(private http: HttpClient) {
  }

  getUser() {
    return this.http.get<User>(this.url);
  }

}
