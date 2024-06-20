import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError } from "rxjs";
import {AuthModel, JwtToken, RegisterUser} from "./auth.model";

@Injectable()
export class AuthenticationDatasource{

    private url: string = "http://localhost:8088/api/v1/auth";

    constructor(private http: HttpClient){ };

    proceedLogin(log: AuthModel):Observable<JwtToken>{
        return this.sendRequest<JwtToken>("POST", `${this.url}/login`, log);
    }


  registerUser(user: RegisterUser):Observable<JwtToken>{
    return this.sendRequest<JwtToken>("POST", `${this.url}/register`, user);
  }



    private sendRequest<T>(verb: string, url: string, body?: AuthModel): Observable<T>{
        return this.http.request<T>(verb, url, {
            body: body
        }).pipe(catchError((error: HttpErrorResponse) => {
          console.log(error)
           alert(error.error.errors);
            throw(`Network Error: ${error.statusText} (${error.status})`)
        }));
    }




}
