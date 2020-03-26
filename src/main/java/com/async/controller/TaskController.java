package com.async.controller;

import com.async.model.Task;
import com.async.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    private Future<Integer> checkTask;

    @GetMapping("/")
    public List<Task> listTasks() {
        return taskService.getTasks();
    }

    @PostMapping("/")
    public ResponseEntity<Task> createTask(@RequestBody @Valid Task task) throws URISyntaxException {
        Task result =  taskService.createTask(task);
        return ResponseEntity.created(new URI("/customers/" + result.getId()))
                .body(result);
    }

    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable Long taskId) {
        return taskService.getTask(taskId);
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{taskId}/execute")
    @ResponseBody
    public ResponseEntity<String> executeTask(@PathVariable Long taskId, @RequestBody Map<String, Integer> data) {
        int x = data.get("x");
        int y = data.get("y");
        checkTask = taskService.executeTask(taskId, x, y);

        return new ResponseEntity<String>("Task started", HttpStatus.OK);
    }
}
