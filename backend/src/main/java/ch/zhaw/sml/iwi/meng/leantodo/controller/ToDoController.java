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

    //Update ToDo
    public void updateToDo(ToDo toDo, Long toDoId, String owner) {
        if(toDo == null){
            return;
        }
        ToDo existingToDo = toDoRepository.findByToDoId(toDoId);
        // Check if the original ToDo was present and that it belonged to the same owner
        if (existingToDo == null || !owner.equals(existingToDo.getOwner())) {
            return;
        }
        //Updating Todo Variables
        existingToDo.setTitle(toDo.getTitle());
        existingToDo.setBeschreibung(toDo.getBeschreibung());
        existingToDo.setPriorisierung(toDo.getPriorisierung());
        existingToDo.setZeitpunkt(toDo.getZeitpunkt());
        existingToDo.setDone(toDo.getDone());
        existingToDo.setFoto(toDo.getFoto());
        toDoRepository.save(existingToDo);
    }

    // Delete Todos
    public void deleteToDo(Long toDoId, String owner) {
        if(toDoId == null){
            return;
        }
        ToDo toDo = toDoRepository.getById(toDoId);
        if (toDo == null || !owner.equals(toDo.getOwner())) {
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