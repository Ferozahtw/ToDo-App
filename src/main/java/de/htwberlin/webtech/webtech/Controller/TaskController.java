package de.htwberlin.webtech.webtech.Controller;

import de.htwberlin.webtech.webtech.Task;
import de.htwberlin.webtech.webtech.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return repository.save(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}/complete")
    public Task markTaskCompleted(@PathVariable Long id) {
        Task task = repository.findById(id).orElseThrow();
        task.setCompleted(true);
        return repository.save(task);
    }
}
