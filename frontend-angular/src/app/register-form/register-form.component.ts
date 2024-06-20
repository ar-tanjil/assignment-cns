import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {JwtToken, RegisterUser} from "../model/authentication/auth.model";
import {AuthenticationDatasource} from "../model/authentication/auth.datasource";
import {LocalStorageService} from "../model/authentication/storageService";
import {JWTTokenService} from "../model/authentication/jwtToken.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.scss']
})
export class RegisterFormComponent {

user: RegisterUser = new RegisterUser();

constructor(   private store: LocalStorageService,
               private jwtService: JWTTokenService,
               private authData: AuthenticationDatasource,
               private route: Router) {

}

  userForm: FormGroup = new FormGroup(
    {
      firstname: new FormControl("",Validators.required),
      lastname: new FormControl(),
      username: new FormControl("",Validators.required),
      role: new FormControl(Validators.required),
      email: new FormControl("",Validators.required),
      password: new FormControl("",Validators.required)
    }
  )



  submitForm(){
    if(!this.userForm.valid){
      return;
    }
    Object.assign(this.user, this.userForm.value);
    console.log(this.user)

    this.authData.registerUser(this.user).subscribe(tok => {
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
