package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import java.security.Principal;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.sml.iwi.meng.leantodo.controller.ProjectController;
import ch.zhaw.sml.iwi.meng.leantodo.controller.ToDoController;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Project;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ToDo;

@RestController
public class ProjectEndpoint {

    @Autowired 
    private ProjectController projectController;

    @Autowired 
    private ToDoController todoController;

    // Get Projects
    @RequestMapping(path = "/api/project", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public List<Project> getProjects(Principal principal) {
        return projectController.listAllProjects(principal.getName());
    }
    
    // Create Project
    @RequestMapping(path = "/api/project", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public void addProject(@RequestBody Project newProject,  Principal principal) {
        projectController.persistProject(newProject, principal.getName(), newProject.getTitle());
    }

    // Delete Project
    @RequestMapping(path = "/api/project/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public void deleteProject(@PathVariable(name="id") Long projectId, Principal principal) {
        System.out.println(projectId);
        projectController.deleteProject(projectId, principal.getName());
    }

    // Update Project
    @RequestMapping(path = "/api/project/{id}", method = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public void updateProject(@RequestBody Project project, @PathVariable(name="id") Long projectId, Principal principal) {
        projectController.updateProject(project, projectId, principal.getName());
    }

    // Get ToDos by ProjectId
    @RequestMapping(path = "/api/project/{id}/todos", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public List<ToDo> getToDo(@PathVariable(name= "id") Long projectId) {
        return todoController.getToDosByProjectId(projectId);
    }

}