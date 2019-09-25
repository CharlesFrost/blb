package com.charlesfrost.blb.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User author;
    @NotBlank
    @Size(min = 2,max = 100)
    private String title;
    @Column(columnDefinition = "TEXT")
    @NotBlank
    @Size(min = 10)
    private String content;
    @NotNull
    private LocalDate date;

    public Post() {
    }

    public Post(User author, @NotBlank @Size(min = 2, max = 100) String title, @NotBlank @Size(min = 10) String content, @NotBlank LocalDate date) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
