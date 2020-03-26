package com.async.service;

import com.async.model.Task;
import com.async.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;
    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }

    public Task getTask(Long taskId) {
       return taskRepository.findById(taskId).orElse(null);

    }

    public void delete(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException(Long.toString(taskId)));
        taskRepository.delete(task);
    }

    @Async
    public Future<Integer> executeTask(Long taskId, int x, int y) {
        log.info("Timer start");
//        System.out.println("Timer start");

        for (int sec = x; sec < y; sec++) {
            if (Thread.currentThread().isInterrupted()) {
                log.info("Thread interrupted");
                return new AsyncResult<>(sec);
            }
            try {
                log.info(sec + " second");
//                System.out.println(sec + " second");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return new AsyncResult<>(sec);
            }
        }
        log.info("Timer end");
//        System.out.println("Timer end");
        return new AsyncResult<>(y);

    }


}
