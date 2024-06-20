package com.cns.assignment.service;

import com.cns.assignment.controller.dto.ProjectDto;
import com.cns.assignment.enums.ProjectStatus;
import com.cns.assignment.exception.DataNotFoundException;
import com.cns.assignment.exception.UnAuthorizedException;
import com.cns.assignment.model.Project;
import com.cns.assignment.model.User;
import com.cns.assignment.repository.ProjectRepository;
import com.cns.assignment.repository.UserRepository;
import com.cns.assignment.repository.UserSpecification;
import com.cns.assignment.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final UserUtils userUtils;

    public Project save(ProjectDto dto){

        Set<User> memeber = new HashSet<>();

        dto.memberId().forEach(id ->
                memeber.add(userRepository.findById(id).orElseThrow()));




        User user = UserUtils.getUser();

        Project project = new Project();
        if (dto.id() != null){
            project = projectRepository.findById(dto.id()).orElseThrow();

            if (!project.getOwner().equals(user)){
                throw  new UnAuthorizedException();
            }
        }

        project.setName(dto.name());
        project.setStatus(ProjectStatus.getEnum(dto.status()));
        project.setOwner(user);
        project.setMembers(memeber);
        project.setStartDate(dto.startDate());
        project.setEndDate(dto.endDate());
        projectRepository.save(project);
        return project;
    }


    public void editProject(ProjectDto dto){
        Project project = projectRepository.findById(dto.id())
                .orElseThrow(DataNotFoundException::new);
        if (!project.getOwner().equals(UserUtils.getUser())){
            throw new UnAuthorizedException();
        }

        if (dto.name() != null){
            project.setName(dto.name());
        }

        if (dto.status() != null){
            project.setStatus(ProjectStatus.getEnum(dto.status()));
        }

        projectRepository.save(project);

    }



    public void delete(Long id){
        Project project = projectRepository.findById(id)
                .orElseThrow(DataNotFoundException::new);
        if (project.getOwner().equals(UserUtils.getUser())){
            projectRepository.delete(project);
        } else {
            throw new UnAuthorizedException();
        }
    }

    public Page<Project> getAllProject(int pageNumber, int pageElement, String sortBy){

        if(sortBy == null){
            sortBy = "id";
        }

        Pageable pageable = PageRequest.of(pageNumber, pageElement, Sort.by(sortBy));

        return projectRepository.findAll(pageable);
    }


    public List<User> getAllUserExpectOwner(){

    return userRepository.findAll(UserSpecification.notTheOwner(UserUtils.getUser().getId()));

    }



}
