package com.spring.csv.model.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @Column(name = "Title", nullable = false)
    private String Title;
    @Column(name = "Author", nullable = false)
    private String Author;
    @Column(name = "Height", length = 50)
    private Integer Height;
    @Column(name = "Genre")
    private String Genre;
    @Column(name = "Publisher", nullable = false)
    private String Publisher;


    public  BookEntity(){

    }

    public BookEntity(Long bookId, String title, String author, Integer height, String genre, String publisher) {
        this.bookId = bookId;
        this.Title = title;
        this.Author = author;
        this.Height = height;
        this.Genre = genre;
        this.Publisher = publisher;
    }


    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String bookAuthor) {
        this.Author = bookAuthor;
    }

    public Integer getHeight() {
        return Height;
    }

    public void setHeight(Integer height) {
        Height = height;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }
}
