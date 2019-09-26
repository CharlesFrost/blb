package com.charlesfrost.blb.dto;

import com.charlesfrost.blb.models.User;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PostDto {
    @NotNull
    @DecimalMin("1")
    private Long authorId;
    @NotBlank
    @Size(min = 2,max = 100)
    private String title;
    @Column(columnDefinition = "TEXT")
    @NotBlank
    @Size(min = 10)
    private String content;
    @NotNull
    private LocalDate date;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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
