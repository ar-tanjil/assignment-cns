import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ProjectListComponent } from './project-list/project-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthenticationDatasource } from './model/authentication/auth.datasource';
import { AuthGuard } from './model/authentication/authGurd';
import { UniversalAppInterceptor } from './model/authentication/httpHeaderService';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
import { ProjectAddFormComponent } from './project-add-form/project-add-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RegisterFormComponent } from './register-form/register-form.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ProjectListComponent,
    ProjectAddFormComponent,
    RegisterFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgbModule,
    HttpClientModule,
    BrowserAnimationsModule,
  ],
  providers: [AuthenticationDatasource,
    AuthGuard,
    UniversalAppInterceptor,
    { provide: HTTP_INTERCEPTORS, useClass: UniversalAppInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
