package com.spring.csv.utils;


import com.spring.csv.model.entity.BookEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVHelper {

    private static final String TYPE = "text/csv";
    private static final String[] HEADERS = {"Title", "Author", "Genre", "Height", "Publisher"};

    public static boolean hashCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<BookEntity> csvToBooks(InputStream inputStream) {

        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            CSVParser parser = CSVParser.parse(fileReader,
                    CSVFormat.DEFAULT.builder()
                            .setHeader(HEADERS)
                            .setIgnoreHeaderCase(true)
                            .setSkipHeaderRecord(true)
                            .setTrim(true)
                            .build());

            List<BookEntity> bookEntityList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = parser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                BookEntity bookEntity = new BookEntity();
                bookEntity.setTitle(csvRecord.get("Title"));
                bookEntity.setAuthor(csvRecord.get("Author"));
                bookEntity.setHeight(Integer.parseInt(csvRecord.get("Height")));
                bookEntity.setGenre(csvRecord.get("Genre"));
                bookEntity.setPublisher(csvRecord.get("Publisher"));
                bookEntityList.add(bookEntity);
            }

            parser.close();
            return bookEntityList;

        } catch (IOException exception) {
            throw new RuntimeException("fail to parse CSV file" + exception.getMessage());
        }
    }
}
