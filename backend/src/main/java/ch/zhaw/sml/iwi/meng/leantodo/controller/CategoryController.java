package ch.zhaw.sml.iwi.meng.leantodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Category;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ToDo;
import ch.zhaw.sml.iwi.meng.leantodo.entity.CategoryRepository;

@Component
public class CategoryController {
    

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listAllCategories(String loginName) {
        return categoryRepository.findByOwner(loginName);
    }

    public void persistProject(Category newCategory, String owner) {
        newCategory.setId(null);
        // We only create empty projects
        newCategory.getToDos().clear();
        newCategory.setOwner(owner);
        //ToDo: Bezeichnung von Projekt noch abfüllen 
        categoryRepository.save(newCategory);
    }

    public void addToDo(Long categoryId, ToDo toDo, String owner) {
        Category category = categoryRepository.getById(categoryId);
        if(category == null || !category.getOwner().equals(owner)) {
            return;
        }  
        // Ensure that JPA creates a new entity
        toDo.setId(null);
        toDo.setOwner(owner);
        category.getToDos().add(toDo);
        categoryRepository.save(category);
    }
    
     // Delete Category
     public void deleteCategory(long categoryId, String owner)
     {
        Category project = categoryRepository.getById(categoryId);
         if(project == null || !project.getOwner().equals(owner)) {
             return;
         }
         categoryRepository.deleteByCategoryId(categoryId);
     }
     
     public void updateCategory(Category category, String owner){
         if(category == null || !category.getOwner().equals(owner)) {
             return;
         }
         category.setOwner(owner); // Set the owner because this property is ignored in the Rest API
         categoryRepository.save(category);
     }
     

}
