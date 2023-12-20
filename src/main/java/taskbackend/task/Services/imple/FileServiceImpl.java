package taskbackend.task.Services.imple;



import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import taskbackend.task.Services.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //File name
        String name =file.getOriginalFilename();

        //randome name generate file
        String randomID= UUID.randomUUID().toString();
        String fileName1=randomID.concat(name.substring(name.lastIndexOf(".")));
        //FullPath
        String filePath=path+ File.separator+"profile_"+fileName1;
        //Create folder if not created
        File f=new File(path);
        if(!f.exists()){
            f.mkdir();
        }
        //File copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return filePath;
    }
}
