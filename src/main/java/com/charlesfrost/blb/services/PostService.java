package com.charlesfrost.blb.services;

import com.charlesfrost.blb.models.Post;
import com.charlesfrost.blb.repositories.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
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
}
