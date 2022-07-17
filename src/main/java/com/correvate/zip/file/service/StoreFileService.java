package com.correvate.zip.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.zip.ZipOutputStream;

public interface StoreFileService {

    public void convertToZip(ZipOutputStream zipOutputStream, List<MultipartFile> files)throws IOException;
}
