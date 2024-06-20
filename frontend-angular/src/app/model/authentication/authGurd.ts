import { JWTTokenService } from './jwtToken.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LocalStorageService } from './storageService';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard {
  constructor(private service: JWTTokenService,
     private router: Router) { }
  



canActivate(
    rotue: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
) : Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree
{
  
    if(this.service.isTokenNonExpired()){
        return true;
    }
    this.router.navigate(["login"])
    this.service.loginCheck();
    return false;
}

}