package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Project;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ProjectRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ToDo;

@Component
public class ProjectController {


    @Autowired
    private ProjectRepository projectRepository;



    public List<Project> listAllProjects(String loginName) {
        return projectRepository.findByOwner(loginName);
    }

    public void persistProject(Project newProject, String owner) {
        newProject.setId(null);
        // We only create empty projects
        newProject.getToDos().clear();
        newProject.setOwner(owner);
        //ToDo: Bezeichnung von Projekt noch abfüllen 
        projectRepository.save(newProject);
    }

    public void addToDo(Long projectId, ToDo toDo, String owner) {
        Project project = projectRepository.getOne(projectId);
        if(project == null || !project.getOwner().equals(owner)) {
            return;
        }  
        // Ensure that JPA creates a new entity
        toDo.setId(null);
        toDo.setOwner(owner);
        project.getToDos().add(toDo);
        projectRepository.save(project);
    }
    
    // Delete Todo
    public void deleteProject(long projectId, String owner)
{
    Project project = projectRepository.getOne(projectId);
    if(project == null || !project.getOwner().equals(owner)) {
        return;
    }
    projectRepository.deleteByProjectId(projectId);
}

public void updateProject(Project project, String owner){
    if(project == null || !project.getOwner().equals(owner)) {
        return;
    }
    project.setOwner(owner); // Set the owner because this property is ignored in the Rest API
    projectRepository.save(project);
}


}