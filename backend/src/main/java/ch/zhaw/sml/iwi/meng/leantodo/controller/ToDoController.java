package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.ToDo;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ToDoRepository;

@Component
public class ToDoController {


    @Autowired
    private ToDoRepository toDoRepository;

    public List<ToDo> listAllToDos(String loginName) {
        return toDoRepository.findByOwner(loginName);
    }

    public void persistToDo(ToDo newToDo, String owner) {
        newToDo.setOwner(owner);
        newToDo.setId(null);
        toDoRepository.save(newToDo);
        //Abfüllen der restlichen Properties 
    }

    public void updateToDo(ToDo toDo, String owner) {
        ToDo orig = toDoRepository.getById(toDo.getId());
        // Check if the original ToDo was present and that it belonged to the same owner
        if(orig == null || !orig.getOwner().equals(owner)) {
            return;
        }
        // Ok, let's overwrite the existing toDo.
        toDo.setOwner(owner); // Set the owner because this property is ignored in the Rest API
        toDoRepository.save(toDo);
    }
    
    // Delete
    public void deleteToDo(long toDoId, String owner)
{
    ToDo toDo = toDoRepository.getById(toDoId);
    if(toDo == null || !toDo.getOwner().equals(owner)) {
        return;
    }
    toDoRepository.deleteById(toDoId);
}

}