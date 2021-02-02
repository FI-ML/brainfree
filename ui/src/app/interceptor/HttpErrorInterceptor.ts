import {Injectable} from "@angular/core";
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {AlertService} from "../services/alert.service";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {

  constructor(private alertService: AlertService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((response: HttpErrorResponse) => {
        this.alertService.error(`${response.status}: ${response.message}`);
        return throwError(response);
      })
    );
  }

}
