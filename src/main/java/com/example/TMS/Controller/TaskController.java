package com.example.TMS.Controller;

import com.example.TMS.Entity.Task;
import com.example.TMS.Entity.User;
import com.example.TMS.Service.TaskService;
import com.example.TMS.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Optional;


@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/new")
    public String newTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("priorities", Task.TaskPriority.values());
        return "task-form";
    }

    @PostMapping("/create")
    public String createTask(@AuthenticationPrincipal OAuth2User oauth2User,
                             @RequestParam String title,
                             @RequestParam String description,
                             @RequestParam Task.TaskPriority priority,
                             @RequestParam(required = false) String dueDate,
                             RedirectAttributes redirectAttributes) {

        String email = oauth2User.getAttribute("email");
        User user = userService.findByEmail(email).orElse(null);

        if (user != null) {
            LocalDateTime dueDateParsed = null;
            if (dueDate != null && !dueDate.isEmpty()) {
                dueDateParsed = LocalDateTime.parse(dueDate + "T00:00:00");
            }

            taskService.createTask(title, description, priority, dueDateParsed, user);
            redirectAttributes.addFlashAttribute("successMessage", "Task created successfully!");
        }

        return "redirect:/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable int id, Model model) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            model.addAttribute("priorities", Task.TaskPriority.values());
            return "task-form";
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable int id,
                             @RequestParam String title,
                             @RequestParam String description,
                             @RequestParam Task.TaskPriority priority,
                             @RequestParam(required = false) String dueDate,
                             RedirectAttributes redirectAttributes) {

        LocalDateTime dueDateParsed = null;
        if (dueDate != null && !dueDate.isEmpty()) {
            dueDateParsed = LocalDateTime.parse(dueDate + "T00:00:00");
        }

        Task updatedTask = taskService.updateTask(id, title, description, priority, dueDateParsed);
        if (updatedTask != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Task updated successfully!");
        }

        return "redirect:/dashboard";
    }

    @PostMapping("/status/{id}")
    public String updateTaskStatus(@PathVariable int id,
                                   @RequestParam Task.TaskStatus status,
                                   @AuthenticationPrincipal OAuth2User oauth2User,
                                   RedirectAttributes redirectAttributes) {

        String email = oauth2User.getAttribute("email");
        User user = userService.findByEmail(email).orElse(null);

        if (user != null) {
            boolean updated = taskService.updateTaskStatus(id, status, user);
            if (updated) {
                redirectAttributes.addFlashAttribute("successMessage", "Task status updated!");
            }
        }

        return "redirect:/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id,
                             @AuthenticationPrincipal OAuth2User oauth2User,
                             RedirectAttributes redirectAttributes) {

        String email = oauth2User.getAttribute("email");
        User user = userService.findByEmail(email).orElse(null);

        if (user != null) {
            boolean deleted = taskService.deleteTask(id, user);
            if (deleted) {
                redirectAttributes.addFlashAttribute("successMessage", "Task deleted successfully!");
            }
        }

        return "redirect:/dashboard";
    }
}