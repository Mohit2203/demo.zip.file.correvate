package com.correvate.zip.file.ControllerTest;

import com.correvate.zip.file.Validation.FileProcessorValidation;
import com.correvate.zip.file.controller.FileProcessController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("unit-test")
public class FileProcessControllerTest {

    @InjectMocks
    private FileProcessController fileProcessController;
    @Mock
    private HttpServletResponse response;

    @Autowired
    MockMvc mockMvc;
    @Mock
    private FileProcessorValidation fileProcessorValidation;


   @Test
    public void test_handleFileUpload() throws Exception {
       List<MultipartFile> multipartFiles = new ArrayList<>();
       ResponseEntity responseEntity=(fileProcessController.uploadFiles(response,multipartFiles));
       Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void test_handleFileUpload_NoFileProvided() throws Exception{
        MockMultipartHttpServletRequestBuilder multipartRequest =
                MockMvcRequestBuilders.multipart("/upload");
        mockMvc.perform(multipartRequest)
                .andExpect(status().isBadRequest());
    }
}
