import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectAddFormComponent } from './project-add-form.component';

describe('ProjectAddFormComponent', () => {
  let component: ProjectAddFormComponent;
  let fixture: ComponentFixture<ProjectAddFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProjectAddFormComponent]
    });
    fixture = TestBed.createComponent(ProjectAddFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
