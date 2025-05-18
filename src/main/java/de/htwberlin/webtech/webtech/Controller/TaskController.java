package de.htwberlin.webtech.webtech.Controller;

import de.htwberlin.webtech.webtech.Task;
import de.htwberlin.webtech.webtech.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // 🔹 Alle Aufgaben anzeigen
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // 🔹 Neue Aufgabe speichern
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    // 🔹 Eine Aufgabe löschen
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    // 🔹 Eine Aufgabe als erledigt markieren
    @PutMapping("/{id}/complete")
    public Task completeTask(@PathVariable Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCompleted(true);
            task.setStatus("erledigt");
            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Aufgabe nicht gefunden");
        }
    }

    // 🔹 Optional: Aufgabe vollständig aktualisieren (z. B. für Bearbeitung)
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setDueDate(updatedTask.getDueDate());
                    task.setPriority(updatedTask.getPriority());
                    task.setStatus(updatedTask.getStatus());
                    task.setRecurrence(updatedTask.getRecurrence());
                    task.setAssignedUser(updatedTask.getAssignedUser());
                    task.setAttachments(updatedTask.getAttachments());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Aufgabe nicht gefunden"));
    }
}
