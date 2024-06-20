import { Component } from '@angular/core';
import { LocalStorageService } from './model/authentication/storageService';
import { JWTTokenService } from './model/authentication/jwtToken.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'assignment';


  
  constructor(
    store: LocalStorageService, 
   private jwtService: JWTTokenService
    ){
      jwtService.loginCheck()
      console.log(this.login);
      
  }

  get login(): boolean{
    return this.jwtService.isLoggedIn; 
  }

}
