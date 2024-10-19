package hhn.aib.thesis.postrest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class PostrestController {
    private static DBService db;

    public PostrestController() {
        try {
            db = new DBService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }


    @GetMapping("/person")
    public Person person(@RequestParam(value = "id") long id) {
        return db.getPerson(id);
    }
}
