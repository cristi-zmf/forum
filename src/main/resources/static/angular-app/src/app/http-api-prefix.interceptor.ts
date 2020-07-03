import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HttpApiPrefixInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const apiPrefix = '/api';
    req = req.clone({
      url: `${window.location.origin}${apiPrefix}${req.url}`
    });
    console.log("Here is my url: %o", window.location.origin);
    console.log("Here is request url: %o", req.url);
    return next.handle(req);
  }
}
