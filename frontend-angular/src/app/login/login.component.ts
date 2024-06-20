import { Component } from '@angular/core';
import { AuthModel, JwtToken } from '../model/authentication/auth.model';
import { LocalStorageService } from '../model/authentication/storageService';
import { JWTTokenService } from '../model/authentication/jwtToken.service';
import { AuthenticationDatasource } from '../model/authentication/auth.datasource';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  authModel: AuthModel;

  constructor(
    private store: LocalStorageService,
    private jwtService: JWTTokenService,
    private authData: AuthenticationDatasource,
    private route: Router
    ){
      this.authModel = new AuthModel();
      store.remove("token");
  }



  loginForm: FormGroup = new FormGroup({
    email: new FormControl(),
    password: new FormControl()
  })


  proceedLogin(){

    if(this.loginForm.valid){
      Object.assign(this.authModel, this.loginForm.value);
      console.log(this.authModel);
      
      this.authData.proceedLogin(this.authModel).subscribe(tok => {
        let token: JwtToken = tok;
        console.log(token);
        
        if(token.token){
          console.log(token.token);
          
          this.store.set("token", token.token);
          this.jwtService.loginCheck(token.token);
          this.route.navigate(["projectList"]);
        }
      } )
    }

  }

}
