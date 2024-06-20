import { Call } from "@angular/compiler";

export class AuthModel{
    constructor(
        public email?: string,
        public password?: string
    ){}
}

export class User{
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
    ){}
}


export class RegisterUser {

  constructor(public firstname?: string,
              public lastname?:  string,
              public username?:  string,
              public role?:      string,
              public email?:     string,
              public password?:  string,) {}

}


export class JwtToken{
    constructor(
        public token?: string
    ){}
}
