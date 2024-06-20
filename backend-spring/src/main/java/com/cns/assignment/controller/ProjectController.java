package com.cns.assignment.controller;

import com.cns.assignment.controller.dto.CustomResponseStatus;
import com.cns.assignment.controller.dto.ProjectDto;
import com.cns.assignment.enums.ProjectStatus;
import com.cns.assignment.model.Project;
import com.cns.assignment.model.User;
import com.cns.assignment.repository.ProjectRepository;
import com.cns.assignment.repository.UserRepository;
import com.cns.assignment.service.ProjectService;
import com.cns.assignment.utils.UserUtils;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/project")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    @GetMapping
    public String checkUser(){

        return "sucess";
    }

    @PostMapping("/save")
    public Project saveProject(@RequestBody ProjectDto dto){
        return projectService.save(dto);
    }

    @GetMapping("/find/{id}")
    public Project findById(@PathVariable("id") Long id){
       return projectRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("/delete/{id}")
    public CustomResponseStatus deleteProject(@PathVariable("id") Long id){
        projectService.delete(id);
      return new CustomResponseStatus(HttpStatus.OK);
    }


    @GetMapping("/all")
    public Iterable<Project> getAllProject(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageElement", required = false, defaultValue = "10") Integer pageElement,
            @Nullable @RequestParam(name = "sortBy", required = false) String sortBy
    ){
        return projectService.getAllProject(pageNumber, pageElement, sortBy);
    }

    @GetMapping("/member")
    public Iterable<User> getAllUserExpectOwner(){
        return projectService.getAllUserExpectOwner();
    }



}
