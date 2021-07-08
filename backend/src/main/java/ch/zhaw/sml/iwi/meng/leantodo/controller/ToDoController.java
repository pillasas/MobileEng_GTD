package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Project;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ProjectRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ToDo;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ToDoRepository;

@Component
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<ToDo> listAllToDos(String loginName) {
        return toDoRepository.findByOwner(loginName);
    }

    public ToDo findById(Long toDoId) {
        return toDoRepository.findByToDoId(toDoId);
    }

    public void addToDo(ToDo newToDo, String owner) {
        newToDo.setOwner(owner);
        newToDo.setId(null);
        toDoRepository.save(newToDo);
        // Abfüllen der restlichen Properties
    }

    public void updateToDo(ToDo toDo, String owner) {
        ToDo orig = toDoRepository.getById(toDo.getId());
        // Check if the original ToDo was present and that it belonged to the same owner
        if (orig == null || !orig.getOwner().equals(owner)) {
            return;
        }
        // Ok, let's overwrite the existing toDo.
        toDo.setOwner(owner); // Set the owner because this property is ignored in
        toDoRepository.save(toDo);
    }

    // Delete Todos
    public void deleteToDo(Long toDoId, String owner) {
        ToDo toDo = toDoRepository.getById(toDoId);
        if (toDo == null || !toDo.getOwner().equals(owner)) {
            return;
        }
        toDoRepository.deleteById(toDoId);
    }

    // Get ToDos by ProjectId
    
    public List<ToDo> getToDosByProjectId(Long projectId) {
        Project p = projectRepository.findById(projectId).get();
        
        if (p == null){
            return new ArrayList<ToDo>();
        }
        return p.getToDos();

    }

}