package de.htwberlin.webtech.webtech.Controller;

import de.htwberlin.webtech.webtech.Task;
import de.htwberlin.webtech.webtech.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return taskService.getTaskById(id)
                .map(task -> {
                    task.setCompleted(true);
                    task.setStatus("erledigt");
                    return taskService.createTask(task); // gleiche Methode wie save
                })
                .orElseThrow(() -> new RuntimeException("Aufgabe nicht gefunden"));
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