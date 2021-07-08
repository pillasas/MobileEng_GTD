package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.qos.logback.core.net.SyslogOutputStream;
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

    public void persistProject(Project newProject, String owner, String title) {
        newProject.setId(null);
        // We only create empty projects
        newProject.getToDos().clear();
        newProject.setOwner(owner);
        newProject.setTitle(title);
        projectRepository.save(newProject);
    }
    
    // Delete Project
    public void deleteProject(Long projectId, String owner) {   
    if(projectId == null){
        return;
    }
    Project project = projectRepository.getById(projectId);
    if(project == null || !owner.equals(project.getOwner())) {
        return;
    }
    projectRepository.deleteByProjectId(projectId);
}

    // Update Project
    public void updateProject(Project project, Long projectId, String owner){
    if(project == null) {
        return;
    }
    Project existingProject = projectRepository.findByProjectId(projectId);
    if (existingProject == null || !owner.equals(existingProject.getOwner())) {
        return;
    }
    existingProject.setTitle(project.getTitle());
    projectRepository.save(existingProject);
}


}