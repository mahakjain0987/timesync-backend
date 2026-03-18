package com.timesync.controller;

import com.timesync.model.Task;
import com.timesync.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")

public class TaskController {

    private final TaskRepository repo;

    public TaskController(TaskRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        return repo.save(task);
    }
    @DeleteMapping("/{id}")
public void deleteTask(@PathVariable String id){
    repo.deleteById(id);
}

    @DeleteMapping("/date/{date}")
    public void deleteTasksByDate(@PathVariable String date) {

        List<Task> tasks = repo.findAll();

        for(Task t : tasks){
            if(t.getDate().equals(date)){
                repo.deleteById(t.getId());
            }
        }
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task updatedTask) {

        Task task = repo.findById(id).orElseThrow();

        task.setTitle(updatedTask.getTitle());
        task.setDate(updatedTask.getDate());

        return repo.save(task);
    }
}