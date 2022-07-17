package com.correvate.zip.file.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Slf4j
public class StoreFileServiceImpl implements StoreFileService{

    private static final Logger logger = LoggerFactory.getLogger(StoreFileServiceImpl.class);

    @Override
    public void convertToZip(ZipOutputStream zipOutputStream, List<MultipartFile> files)
            throws IOException
            {
            ZipEntry zipEntry = null;
            FileInputStream fileInputStream = null;

               for (MultipartFile file : files) {
                    zipEntry = new ZipEntry(file.getOriginalFilename());
                    zipOutputStream.putNextEntry(zipEntry);
                    fileInputStream = new FileInputStream(convertMultiPartToFile(file));
                    IOUtils.copy(fileInputStream, zipOutputStream);
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }

    }
    /*
    This Method Contains the Logic to Convert The MultiPartFile Into File
     */
    private  File convertMultiPartToFile(MultipartFile file ) throws IOException {
        File convFile = new File( file.getOriginalFilename() );
        FileOutputStream  outputStream= new FileOutputStream( convFile );
        outputStream.write( file.getBytes());
        outputStream.close();
        return convFile;
    }
}
