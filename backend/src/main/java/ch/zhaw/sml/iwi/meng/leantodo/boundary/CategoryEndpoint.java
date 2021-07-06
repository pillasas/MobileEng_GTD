package ch.zhaw.sml.iwi.meng.leantodo.boundary;

import java.security.Principal;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.sml.iwi.meng.leantodo.controller.CategoryController;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Category;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ToDo;

@RestController
public class CategoryEndpoint {
    
    @Autowired 
    private CategoryController categoryController;

    @RequestMapping(path = "/api/category", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public List<Category> getCategories(Principal principal) {
        List<Category> res = categoryController.listAllCategories(principal.getName());
        System.out.println(res);
        return res;
    }
    
    @RequestMapping(path = "/api/category/{id}", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public void addToDo(@RequestParam(name="id") Long categoryId,  @RequestBody ToDo toDo,  Principal principal) {
        categoryController.addToDo(categoryId, toDo, principal.getName());
    }



}
