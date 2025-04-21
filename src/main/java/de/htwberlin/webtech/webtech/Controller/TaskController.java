package de.htwberlin.webtech.webtech.Controller;

import de.htwberlin.webtech.webtech.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*") // erlaubt Zugriff vom Frontend (Vue.js)
public class TaskController {

    @GetMapping
    public List<Task> getDummyTasks() {
        Task task1 = new Task("Webtechnologie lernen", "M1 bis 20. April erledigen");
        Task task2 = new Task("Vue.js vorbereiten", "Für M2 ein v-for schreiben");

        return List.of(task1, task2);
    }
}
