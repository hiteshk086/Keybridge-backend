package taskbackend.task.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String duedate;

    private String createdby;

    private String client;

    private String tasks;

    private String status;

    private  String assignee;

    private String taskType;

    private String priority;

    private String notes;

    private String emailNotes;

    private String project;
}
