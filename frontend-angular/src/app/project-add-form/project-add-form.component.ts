import { Component } from '@angular/core';
import { User } from '../model/authentication/auth.model';
import { ProjectDatasource } from '../model/project/project.datasource';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { ProjectPost, ProjectTable } from '../model/project/project.model';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, switchMap } from 'rxjs';
import {JWTTokenService} from "../model/authentication/jwtToken.service";

@Component({
  selector: 'app-project-add-form',
  templateUrl: './project-add-form.component.html',
  styleUrls: ['./project-add-form.component.scss']
})
export class ProjectAddFormComponent {

  users: User[];
  project: ProjectPost = new ProjectPost();


  constructor(private projectData: ProjectDatasource,
    private router: Router,
    private route: ActivatedRoute
  ){



    this.users = new Array<User>();
    projectData.getUserMember().subscribe(u => {
      this.users = u;
      console.log(u);

    })
  }

  keywordGroup = new FormArray([
    this.createRequirementFormControl()
  ])



  submitForm(){
    Object.assign(this.project, this.projectForm.value);
    console.log(this.project);
    this.projectData.postProject(this.project).subscribe(p => {
      this.router.navigateByUrl("projectList");
      console.log("Successs");
    });
  }

  projectForm: FormGroup = new FormGroup(
    {
      id: new FormControl(),
      name: new FormControl("",Validators.required),
      status: new FormControl("",Validators.required),
      startDate: new FormControl(),
      endDate: new FormControl(),
      memberId: this.keywordGroup
    }
  )

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if(id){
      this.projectData.getProjectById(id as unknown as number).subscribe(p => {
        let projetTable = new ProjectTable();
        projetTable = p;
        this.project.id = p.id;
        this.project.name = p.name;
        this.project.status = this.getStatusValue(p.status?.toLocaleUpperCase());
        this.project.startDate = p.startDate;
        this.project.endDate = p.endDate;
        let members: number[] = new Array<number>();
        p.members?.forEach(a => members.push(a.id!) )
        this.project.memberId = members;
        this.projectForm.patchValue(this.project);
      });
    }
  }

  getStatusValue(status? : String){

  switch (status) {
    case "PRE":
        return 0;
        case "START":
          return 1;
          case "END":
            return 3;
    default:
      return 0;

  }
  }



  createRequirementFormControl(): FormControl{
    return new FormControl();
  }

  addRequirement(){
    if(this.users.length > 0 && this.keywordGroup.length <5){
      this.keywordGroup.push(this.createRequirementFormControl())
    }
  }

  removeRequirement(index: number){
    this.keywordGroup.removeAt(index)
  }

}
