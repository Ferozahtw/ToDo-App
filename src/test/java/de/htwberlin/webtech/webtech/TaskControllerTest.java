package de.htwberlin.webtech.webtech;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import de.htwberlin.webtech.webtech.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TaskRepository taskRepository;

    @AfterEach
    void cleanup() {
        taskRepository.deleteAll();
    }

    @Test
    void testCreateAndGetTask() throws Exception {
        Task task = new Task();
        task.setTitle("Testaufgabe");
        task.setDescription("Etwas testen");
        task.setStatus("offen");
        task.setPriority(1);
        task.setRecurring("none");
        task.setUser("feroza");
        task.setDueDate("2025-07-01");

        // ➕ Aufgabe erstellen
        mockMvc.perform(post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Testaufgabe"));

        // 🔍 Aufgaben abrufen
        mockMvc.perform(get("/task"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Testaufgabe"));
    }

    @Test
    void testDeleteTask() throws Exception {
        Task task = new Task();
        task.setTitle("ToDelete");
        task.setDescription("Desc");
        task.setStatus("offen");
        task.setPriority(1);
        task.setRecurring("none");
        task.setUser("user");
        task.setDueDate("2025-07-01");

        String response = mockMvc.perform(post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Task created = objectMapper.readValue(response, Task.class);

        mockMvc.perform(delete("/task/{id}", created.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/task"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void testCompleteTask() throws Exception {
        Task task = new Task();
        task.setTitle("Complete");
        task.setDescription("Desc");
        task.setStatus("offen");
        task.setPriority(1);
        task.setRecurring("none");
        task.setUser("user");
        task.setDueDate("2025-07-01");

        String response = mockMvc.perform(post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Task created = objectMapper.readValue(response, Task.class);

        mockMvc.perform(put("/task/{id}/complete", created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completed").value(true))
                .andExpect(jsonPath("$.status").value("erledigt"));
    }

    @Test
    void testUpdateTask() throws Exception {
        Task task = new Task();
        task.setTitle("OldTitle");
        task.setDescription("OldDesc");
        task.setStatus("offen");
        task.setPriority(1);
        task.setRecurring("none");
        task.setUser("user");
        task.setDueDate("2025-07-01");

        String response = mockMvc.perform(post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Task created = objectMapper.readValue(response, Task.class);

        Task updated = new Task();
        updated.setTitle("NewTitle");
        updated.setDescription("NewDesc");
        updated.setStatus("in progress");
        updated.setPriority(2);
        updated.setRecurring("weekly");
        updated.setUser("user2");
        updated.setDueDate("2025-08-01");

        mockMvc.perform(put("/task/{id}", created.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("NewTitle"))
                .andExpect(jsonPath("$.description").value("NewDesc"));

        mockMvc.perform(get("/task"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("NewTitle"));
    }

    @Test
    void testCompleteTaskNotFound() throws Exception {
        mockMvc.perform(put("/task/{id}/complete", 9999))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllTasksEmpty() throws Exception {
        mockMvc.perform(get("/task"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

}
