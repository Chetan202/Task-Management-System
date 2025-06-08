package com.example.TMS.Service;

import com.example.TMS.Entity.Task;
import com.example.TMS.Entity.User;
import com.example.TMS.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasksByUser(User user) {
        return taskRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public List<Task> getTasksByUserAndStatus(User user, Task.TaskStatus status) {
        return taskRepository.findByUserAndStatusOrderByCreatedAtDesc(user, status);
    }

    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Task createTask(String title, String description, Task.TaskPriority priority,
                           java.time.LocalDateTime dueDate, User user) {
        Task task = new Task(title, description, priority, dueDate, user);
        return taskRepository.save(task);
    }

    public Task updateTask(int id, String title, String description,
                           Task.TaskPriority priority, java.time.LocalDateTime dueDate) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(title);
            task.setDescription(description);
            task.setPriority(priority);
            task.setDueDate(dueDate);
            task.setUpdatedAt(java.time.LocalDateTime.now());
            return taskRepository.save(task);
        }
        return null;
    }

    public boolean updateTaskStatus(int id, Task.TaskStatus status, User user) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();

            if (task.getUser().getId().equals(user.getId())) {
                task.setStatus(status);
                taskRepository.save(task);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(int id, User user) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();

            if (task.getUser().getId().equals(user.getId())) {
                taskRepository.delete(task);
                return true;
            }
        }
        return false;
    }

    public long getTaskCountByStatus(User user, Task.TaskStatus status) {
        return taskRepository.countByUserAndStatus(user, status);
    }
}