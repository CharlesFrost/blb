package com.charlesfrost.blb.controllers;

import com.charlesfrost.blb.dto.PostDto;
import com.charlesfrost.blb.exceptions.ResourceNotFoundException;
import com.charlesfrost.blb.models.Post;
import com.charlesfrost.blb.models.ResponseBody;
import com.charlesfrost.blb.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
@CrossOrigin
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

    @GetMapping("/{id")
    public ResponseEntity getPage(@PathVariable(name = "id") Long id) {
        try {
            Post post = postService.findById(id);
            return ResponseEntity.status(200).body(new ResponseBody("Sukces!",post));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping
    public ResponseEntity savePost(@RequestBody @Valid PostDto postDto) {
        Post post = postService.mapToPost(postDto);
        postService.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePost(@PathVariable(name = "id") Long id, @RequestBody @Valid PostDto postDto) {
        try {
            Post postToEdit = postService.findById(id);
            Post post = postService.mapToPost(postDto);
            post.setId(postToEdit.getId());
            postService.save(post);
            return ResponseEntity.ok(post);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseBody("Nie ma takiego posta", postDto));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable(name = "id") Long id) {
        postService.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
