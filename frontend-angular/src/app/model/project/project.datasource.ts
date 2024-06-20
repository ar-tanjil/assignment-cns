import { Injectable } from "@angular/core";
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import { Observable, catchError } from "rxjs";
import { ProjectPost, ProjectTable } from "./project.model";
import { Page } from "../page";
import { ResponseEntity } from "../common.model";
import { User } from "../authentication/auth.model";

@Injectable({
    providedIn: "root"
})
export class ProjectDatasource {

    private projectURL: string = "http://localhost:8088/api/v1/project";



    constructor(private http: HttpClient) { };

    postProject(project : ProjectPost):Observable<ProjectPost>{
        return this.sendRequest<ProjectPost>("POST", `${this.projectURL}/save`, project);
    }

    deleteProjectById(id: number):Observable<ResponseEntity>{
        return this.sendRequest<ResponseEntity>("DELETE", `${this.projectURL}/delete/${id}`);
    }

    getPorject(): Observable<Page<ProjectTable>> {
        return this.sendRequest<Page<ProjectTable>>("GET", `${this.projectURL}/all`);
    }

    getUserMember():Observable<User[]>{
        return this.sendRequest<User[]>("GET", `${this.projectURL}/member`);
    }


    getProjectById(id: number):Observable<ProjectTable>{
        return this.sendRequest<ProjectTable>("GET", `${this.projectURL}/find/${id}`);
    }





    private sendRequest<T>(verb: string, url: string, body?: T, params?:HttpParams): Observable<T> {

        return this.http.request<T>(verb, url, {
            body: body,
            params: params
        }).pipe(catchError((error: HttpErrorResponse) => {

          alert(error.error.errors);
          throw(`Network Error: ${error.statusText} (${error.status})`)
        }));
    }

}
