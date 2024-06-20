import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectListComponent } from './project-list/project-list.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './model/authentication/authGurd';
import { ProjectAddFormComponent } from './project-add-form/project-add-form.component';
import {RegisterFormComponent} from "./register-form/register-form.component";

const routes: Routes = [
  {path: "login",
    component: LoginComponent
  },
  {path: "registerForm",
    component: RegisterFormComponent,

  },
  {path: "projectAddForm",
    component: ProjectAddFormComponent,
    canActivate:[AuthGuard]
  },
  {path: "projectAddForm/:id",
    component: ProjectAddFormComponent,
    canActivate:[AuthGuard]
  },
  {
    path : "projectList",
    component: ProjectListComponent,
    canActivate:[AuthGuard]
  },
  { path: "", redirectTo: "/login", pathMatch: "full" }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
