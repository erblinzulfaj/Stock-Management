package com.gebruderzulfajstock.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return new ResponseEntity<>("Please upload a file!", HttpStatus.BAD_REQUEST);
//        }
//
//        try (InputStream inputStream = file.getInputStream()) {
//            Workbook workbook = WorkbookFactory.create(inputStream);
//            Sheet sheet = workbook.getSheetAt(0); // Lexon fletën e parë
//            List<String> data = new ArrayList<>();
//
//            for (Row row : sheet) {
//                for (Cell cell : row) {
//                    data.add(cell.toString());
//                }
//            }
//
//            // Shtypni të dhënat ose bëni diçka tjetër me to
//            data.forEach(System.out::println);
//
//            workbook.close();
//            return new ResponseEntity<>("File uploaded and read successfully!", HttpStatus.OK);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Failed to read file!", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
