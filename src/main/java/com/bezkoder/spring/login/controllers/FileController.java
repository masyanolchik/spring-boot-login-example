package com.bezkoder.spring.login.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.IOUtils;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class FileController {
    @GetMapping(
            value = "/Users/maxymshaposhnyk/IdeaProjects/dyplom/product_pics/**",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getFile(HttpServletRequest request) throws IOException {
        String fileName = request.getRequestURL().toString().split(":8080")[1];
        InputStream in = Files.newInputStream(Paths.get(fileName));
        return IOUtils.toByteArray(in);
    }
}