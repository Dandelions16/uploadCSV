package com.spring.csv.service;

import com.spring.csv.model.entity.BookEntity;
import com.spring.csv.model.repo.BookRepo;
import com.spring.csv.utils.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service("bookService")
@Transactional
public class BookService {
    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<BookEntity> save(MultipartFile file) {
        try {
            List<BookEntity> bookEntities = CSVHelper.csvToBooks(file.getInputStream());
            return bookRepo.saveAll(bookEntities);

        } catch (IOException exception) {
            throw new RuntimeException("Fail to store csv data : " + exception.getMessage());
        }

    }

    public List<BookEntity> findAll() {
        return bookRepo.findAll();
    }
}
