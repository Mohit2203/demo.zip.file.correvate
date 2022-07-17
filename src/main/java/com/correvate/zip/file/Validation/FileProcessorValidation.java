package com.correvate.zip.file.Validation;

import com.correvate.zip.file.Exception.FilesNotFoundExceptions;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class FileProcessorValidation {

    @SneakyThrows
    public void validateFile(List<MultipartFile> files){
            for (MultipartFile file: files) {
                if(file.getOriginalFilename().isEmpty() || file.getContentType().isEmpty()){
                    throw new FilesNotFoundExceptions();
                }
            }

    }
}
