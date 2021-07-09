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

        ToDo toDo4 = new ToDo();
        toDo4.setTitle("Machthaber ausschalten");
        toDo4.setOwner("Onay");
        toDo4.setBeschreibung("Arnold Schwarzenegger auschalten");
        toDo4.setPriorisierung(2);
        toDo4.setZeitpunkt(new Date());
        toDoRepository.save(toDo4);

        ToDo toDo5 = new ToDo();
        toDo5.setTitle("Spion engagieren");
        toDo5.setOwner("Onay");
        toDo5.setBeschreibung("Einen Spion engagieren und einschleusen beim Feind");
        toDo5.setPriorisierung(2);
        toDo5.setZeitpunkt(new Date());
        toDoRepository.save(toDo5);

        ToDo toDo6 = new ToDo();
        toDo6.setTitle("Panzer kaufen");
        toDo6.setOwner("Sasha");
        toDo6.setBeschreibung("Panzer auf dem Schwarzmarkt kaufen");
        toDo6.setPriorisierung(2);
        toDo6.setZeitpunkt(new Date());
        toDoRepository.save(toDo6);

        // Listen für Project/Cat vorbereiten
        List<ToDo> toDoList1 = new ArrayList<>();
        toDoList1.add(toDo1);
        toDoList1.add(toDo2);
        toDoList1.add(toDo3);

        List<ToDo> toDoList2 = new ArrayList<>();
        toDoList2.add(toDo4);
        toDoList2.add(toDo5);

        List<ToDo> toDoList3 = new ArrayList<>();
        toDoList3.add(toDo6);
        
        //Category hinzufügen
        Category cat1 = new Category();
        cat1.setTitle("Körperliche Aktivitäten");
        cat1.setOwner("Onay");
        cat1.setToDos(toDoList1);
        categoryRepository.save(cat1);

        Category cat2 = new Category();
        cat2.setTitle("Macht");
        cat2.setOwner("Sasha");
        cat2.setToDos(toDoList3);
        categoryRepository.save(cat2);

        Category cat3 = new Category();
        cat3.setTitle("Willensdurchsetzung");
        cat3.setOwner("Onay");
        cat3.setToDos(toDoList2);
        categoryRepository.save(cat3);


        //Project hinzufügen
        Project proj1 = new Project();
        proj1.setTitle("Übernahme der Welt");
        proj1.setOwner("Onay");
        proj1.setToDos(toDoList2);
        projectRepository.save(proj1);

        Project proj2 = new Project();
        proj2.setTitle("World War 3");
        proj2.setOwner("Sasha");
        proj2.setToDos(toDoList3);
        projectRepository.save(proj2);

        Project proj3 = new Project();
        proj3.setTitle("Raumfahrtprogramm");
        proj3.setOwner("Sasha");
        projectRepository.save(proj3);

        Project proj4 = new Project();
        proj4.setTitle("Sekte gründen");
        proj4.setOwner("Sasha");
        projectRepository.save(proj4);

        Project proj5 = new Project();
        proj5.setTitle("Weltfrieden");
        proj5.setOwner("user");
        projectRepository.save(proj5);

    }
}
