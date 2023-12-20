package taskbackend.task.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import taskbackend.task.Entity.TaskEntity;
import taskbackend.task.Repository.taskRepository;
import taskbackend.task.Services.FileService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class taskController {
    final ObjectMapper mapper;
    private final FileService fileService;

    @Value("${project.image}")
    private String path;
    public final taskRepository taskRepo;

    public taskController(ObjectMapper mapper, FileService fileService, taskRepository taskRepo) {
        this.mapper = mapper;
        this.fileService = fileService;
        this.taskRepo = taskRepo;
    }

    @PostMapping("/addTask")
    private ResponseEntity<Object> get(@RequestParam String data, @RequestParam (required = false) MultipartFile image)
    {
        try {
            // Read value from the Requested Data
            TaskEntity task = mapper.readValue(data,TaskEntity.class);
            String filename = "";
            if (image == null){
                task.setProject("");
            } else {
                try {
                    filename = this.fileService.uploadImage(path,image);
                    task.setProject(filename);
                } catch (IOException e) {
                    task.setProject("");
                    throw new RuntimeException(e);
               }
                }
            // Save data in dataBase
            taskRepo.save(task);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        Map<String, Boolean> taskData = new HashMap<>();
        taskData.put("status", true);
        return new ResponseEntity<>(taskData, HttpStatus.OK);
    }
}
