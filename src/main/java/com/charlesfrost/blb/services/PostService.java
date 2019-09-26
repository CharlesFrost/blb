package com.charlesfrost.blb.services;

import com.charlesfrost.blb.dto.PostDto;
import com.charlesfrost.blb.exceptions.ResourceNotFoundException;
import com.charlesfrost.blb.models.Post;
import com.charlesfrost.blb.models.User;
import com.charlesfrost.blb.repositories.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public Post getOne(Long id) {
        return postRepository.getOne(id);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Page<Post> showPage(int page, int size) {
        return postRepository.findAll(PageRequest.of(page,size));
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono!"));
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public Post mapToPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDate(postDto.getDate());
        post.setContent(postDto.getContent());
        User author = userService.findById(postDto.getAuthorId());
        post.setAuthor(author);
        return post;
    }
}
