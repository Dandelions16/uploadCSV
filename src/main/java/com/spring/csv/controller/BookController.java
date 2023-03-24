package com.spring.csv.controller;

import com.spring.csv.dto.ResponData;
import com.spring.csv.model.entity.BookEntity;
import com.spring.csv.service.BookService;
import com.spring.csv.utils.CSVHelper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<?> findAllBooks(){
        ResponData responData = new ResponData();
        try {
            List<BookEntity> bookEntities = bookService.findAll();
            responData.setStatus(true);
            responData.getMessage().add("Berhasil Menampilkan data buku");
            responData.setPayload(bookEntities);
            return ResponseEntity.ok(responData);

        }catch (Exception exception){
            responData.setStatus(false);
            responData.getMessage().add("Could not get books"+ exception.getMessage());
            responData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responData);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        ResponData responData = new ResponData();

        if (!CSVHelper.hashCSVFormat(file)){
            responData.setStatus(false);
            responData.setPayload(null);
            responData.getMessage().add("it's not a csv file");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responData);
        }
        try {

            List<BookEntity> bookEntities = bookService.save(file);
            responData.setStatus(true);
            responData.setPayload(bookEntities);
            responData.getMessage().add("Uploaded the file success : " + file.getOriginalFilename());
            return ResponseEntity.ok(responData);

        } catch (Exception exception){
            responData.setStatus(false);
            responData.getMessage().add("Could Not upload the file: " + file.getOriginalFilename());
            responData.setPayload(null);
//            exception.printStackTrace();  // Tambahkan ini untuk mencetak pesan error ke konsol
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responData);
        }
    }
}