package taskbackend.task.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class fileUpload {

    public String FileUpload(String path, MultipartFile image) throws IOException {
        String name = image.getOriginalFilename();

        String filePath = path + File.separator+name;

        File file = new File(path);
        if(!file.exists())
        {
            file.mkdir();
        }

        Files.copy(image.getInputStream(), Paths.get(filePath));

        return filePath;
    }

}
