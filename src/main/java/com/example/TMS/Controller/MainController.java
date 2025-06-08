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
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal OAuth2User oauth2User, Model model){
        String email = oauth2User.getAttribute("email");
        User user = userService.findByEmail(email).orElse(null);

        // If user doesn't exist in database, create them
        if(user == null) {
            user = new User();
            user.setEmail(email);
            user.setName(oauth2User.getAttribute("name"));
            user.setPicture(oauth2User.getAttribute("picture"));
            user = userService.save(user); // You'll need to add this method to UserService

            System.out.println("Created new user: " + user.getName() + " (" + user.getEmail() + ")");
        }

        if(user != null){
            List<Task> allTasks = taskService.getAllTasksByUser(user);
            Long pendingCount = taskService.getTaskCountByStatus(user, Task.TaskStatus.PENDING);
            Long inProgressCount = taskService.getTaskCountByStatus(user, Task.TaskStatus.IN_PROGRESS);
            Long completedCount = taskService.getTaskCountByStatus(user, Task.TaskStatus.COMPLETED);

            model.addAttribute("user", user);
            model.addAttribute("tasks", allTasks);
            model.addAttribute("pendingCount", pendingCount != null ? pendingCount : 0L);
            model.addAttribute("inProgressCount", inProgressCount != null ? inProgressCount : 0L);
            model.addAttribute("completedCount", completedCount != null ? completedCount : 0L);

            System.out.println("Dashboard loaded for: " + user.getName() +
                    " | Tasks: " + allTasks.size() +
                    " | Pending: " + pendingCount +
                    " | In Progress: " + inProgressCount +
                    " | Completed: " + completedCount);
        }

        return "dashboard";
    }
}