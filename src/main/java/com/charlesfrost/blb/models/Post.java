package com.charlesfrost.blb.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    @NotBlank
    private LocalDate date;
}
