package taskbackend.task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskbackend.task.Entity.TaskEntity;

public interface taskRepository extends JpaRepository<TaskEntity, String> {
}
