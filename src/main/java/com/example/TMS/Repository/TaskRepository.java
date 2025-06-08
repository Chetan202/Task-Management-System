package com.example.TMS.Repository;

import com.example.TMS.Entity.Task;
import com.example.TMS.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findByUserOrderByCreatedAtDesc(User user);
    List<Task> findByUserAndStatusOrderByCreatedAtDesc(User user, Task.TaskStatus status);

    @Query("SELECT t FROM Task t WHERE t.user = ?1 AND t.status = ?2")
    List<Task> findTasksByUserAndStatus(User user, Task.TaskStatus status);

    Integer countByUserAndStatus(User user, Task.TaskStatus status);

}
