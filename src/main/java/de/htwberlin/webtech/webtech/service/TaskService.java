package de.htwberlin.webtech.webtech.service;

import de.htwberlin.webtech.webtech.Task;
import de.htwberlin.webtech.webtech.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setDueDate(updatedTask.getDueDate());
                    task.setPriority(updatedTask.getPriority());
                    task.setRecurring(updatedTask.getRecurring());
                    task.setAttachments(updatedTask.getAttachments());
                    task.setStatus(updatedTask.getStatus());
                    task.setCompleted(updatedTask.isCompleted());
                    task.setUser(updatedTask.getUser());
                    return taskRepository.save(task);
                })
                .orElse(null);
    }

    public boolean deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            return false;
        }
        taskRepository.deleteById(id);
        return true;
    }
}
