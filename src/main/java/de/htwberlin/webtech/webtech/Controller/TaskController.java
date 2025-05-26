package de.htwberlin.webtech.webtech.Controller;

import de.htwberlin.webtech.webtech.Task;
import de.htwberlin.webtech.webtech.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 🔹 Alle Aufgaben anzeigen
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // 🔹 Neue Aufgabe speichern
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    // 🔹 Eine Aufgabe löschen
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    // 🔹 Eine Aufgabe als erledigt markieren
    @PutMapping("/{id}/complete")
    public Task completeTask(@PathVariable Long id) {
        Task task = taskService.getTaskById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aufgabe nicht gefunden"));

        task.setCompleted(true);
        task.setStatus("erledigt");
        return taskService.createTask(task); // save()
    }

    // 🔹 Optional: Aufgabe vollständig aktualisieren
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task result = taskService.updateTask(id, updatedTask);
        if (result == null) {
            throw new RuntimeException("Aufgabe nicht gefunden");
        }
        return result;
    }
}