import { Component } from '@angular/core';
import { ProjectDatasource } from '../model/project/project.datasource';
import { JWTTokenService } from '../model/authentication/jwtToken.service';
import { ProjectTable } from '../model/project/project.model';
import { ReplaySubject } from 'rxjs';
import { User } from '../model/authentication/auth.model';
import swal from 'sweetalert2'
import { LocalStorageService } from '../model/authentication/storageService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.scss']
})
export class ProjectListComponent {


  admin: boolean = false;
  projects: ProjectTable[];
  member: User[];
  replaySubject: ReplaySubject<ProjectTable[]>;
  username: string |null;


  constructor(
    private projectData: ProjectDatasource,
    private jwtService: JWTTokenService,
    private storService: LocalStorageService,
    private route: Router
  ) {
    this.projects = new Array<ProjectTable>();
    this.member = new Array<User>();
    this.replaySubject = new ReplaySubject<ProjectTable[]>(1);
    this.getAllProject();
    this.admin = jwtService.getRole() == "ADMIN";

    this.username = jwtService.getUser();

  }



  getAllProject() {
    this.projectData.getPorject().subscribe(project => {
      console.log(project);

      this.projects = project.content;
      this.replaySubject.next(project.content);
      this.replaySubject.complete();
    })

  }


  logOut(){
      this.storService.remove('token');
      this.jwtService.loginCheck();
      this.route.navigate(['login']);
  }



  deleteDialog(id?: number){


    if (id == null || id < 0) {
      return;
    }

    let result =  window.confirm("Are you sure you want to delete");

    if(result){
      this.projectData.deleteProjectById(id).subscribe(a => {
        let index = this.projects.findIndex(a => a.id == id);
        this.projects.splice(index, 1);

      });
    }

  }


}
