package com.charlesfrost.blb.controllers;

import com.charlesfrost.blb.models.Post;
import com.charlesfrost.blb.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<Page<Post>> getPages(@RequestParam(name = "page",defaultValue = "0") int page, @RequestParam(name = "size",defaultValue = "1") int size) {
        Page<Post> posts = postService.showPage(page,size);
        return ResponseEntity.ok(posts);
    }
}
