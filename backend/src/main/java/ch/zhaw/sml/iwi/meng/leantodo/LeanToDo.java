package ch.zhaw.sml.iwi.meng.leantodo;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Category;
import ch.zhaw.sml.iwi.meng.leantodo.entity.CategoryRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ProjectRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Role;
import ch.zhaw.sml.iwi.meng.leantodo.entity.RoleRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ToDo;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ToDoRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.User;
import ch.zhaw.sml.iwi.meng.leantodo.entity.UserRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Project;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ProjectRepository;


@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class LeanToDo implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LeanToDo.class, args);
        
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    
    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    // This is only really relevant for development, where we have different servers for frontend and backend
                    .allowedOrigins("http://localhost:8100")
                    .allowedMethods("GET", "PUT", "POST", "DELETE")
                    // AllowCredentials is necessary, as it sets 'Access-Control-Allow-Credentials'. 
                    // Otherwise Angular's HttpClient will not pass the Cookie back.
                    .allowCredentials(true);
            }
        };
    }

    @Override
    public void run(String... args) throws Exception {
        //User hinzufügen (3x)
        User u = new User();
        u.setLoginName("user");
        u.setPasswordHash(new BCryptPasswordEncoder().encode("user"));

        User sa = new User();
        sa.setLoginName("Sasha");
        sa.setPasswordHash(new BCryptPasswordEncoder().encode("Sasha"));

        User on = new User();
        on.setLoginName("Onay");
        on.setPasswordHash(new BCryptPasswordEncoder().encode("Onay"));

        //Rollen definieren
        Role r = new Role();
        r.setRoleName("ROLE_USER");
        roleRepository.save(r);

        //Rollen Usern hinzufügen
        u.getRoles().add(r);
        userRepository.save(u);

        sa.getRoles().add(r);
        userRepository.save(sa);

        on.getRoles().add(r);
        userRepository.save(on);


        //ToDos definieren (3x)
        ToDo toDo1 = new ToDo();
        toDo1.setTitle("Fische fagen");
        toDo1.setOwner("Sasha");
        toDo1.setBeschreibung("Fische fangen mit Klasse.");
        toDo1.setPriorisierung(2);
        toDo1.setZeitpunkt(new Date());
        toDoRepository.save(toDo1);

        ToDo toDo2 = new ToDo();
        toDo2.setTitle("Eier sammeln");
        toDo2.setOwner("Onay");
        toDo2.setBeschreibung("Auf Eierreise.");
        toDo2.setPriorisierung(2);
        toDo2.setZeitpunkt(new Date());
        toDoRepository.save(toDo2);

        ToDo toDo3 = new ToDo();
        toDo3.setTitle("Training");
        toDo3.setOwner("Sasha");
        toDo3.setBeschreibung("Arnold Schwarzenegger werden");
        toDo3.setPriorisierung(2);
        toDo3.setZeitpunkt(new Date());
        toDoRepository.save(toDo3);

        List<ToDo> toDoList = new ArrayList<>();
        toDoList.add(toDo1);
        toDoList.add(toDo2);
        toDoList.add(toDo3);
        
        //Category hinzufügen
        Category cat1 = new Category();
        cat1.setTitle("Körperliche Aktivitäten");
        cat1.setOwner("Onay");
        cat1.setToDos(toDoList);
        categoryRepository.save(cat1);


        //Project hinzufügen
        Project proj1 = new Project();
        proj1.setTitle("Weltherrschaft");
        proj1.setOwner("Onay");
        proj1.setToDos(toDoList);
        projectRepository.save(proj1);

    }
}
