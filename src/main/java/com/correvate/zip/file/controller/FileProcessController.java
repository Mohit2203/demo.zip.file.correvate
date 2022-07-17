package com.correvate.zip.file.controller;

import com.correvate.zip.file.Validation.FileProcessorValidation;
import com.correvate.zip.file.service.StoreFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * @author Mohit
 *
 */
@RestController
public class FileProcessController {

    @Autowired
    private StoreFileService storeFileService;

    @Autowired
    private FileProcessorValidation fileProcessorValidation;

    @PostMapping(value="/upload",consumes ={MediaType.MULTIPART_FORM_DATA_VALUE},
            produces={"application/zip", MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StreamingResponseBody> uploadFiles(HttpServletResponse response,
                                                             @RequestPart("files") List<MultipartFile> files) {
        fileProcessorValidation.validateFile(files);
            return ResponseEntity
                    .ok().header("Content-Disposition", "attachment; filename=\"Documents.zip\"")
                    .body(outputStream -> {
                        final ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
                            storeFileService.convertToZip(zipOutputStream, files);
                    });
    }
}

