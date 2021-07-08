package ch.zhaw.sml.iwi.meng.leantodo.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo,Long> {
    public List<ToDo> findByOwner(String owner);
    
    public void deleteById(Long toDoId);

    public ToDo findByToDoId(Long toDoId);

}