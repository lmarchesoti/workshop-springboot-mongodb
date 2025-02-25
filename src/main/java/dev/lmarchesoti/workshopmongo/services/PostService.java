package dev.lmarchesoti.workshopmongo.services;

import dev.lmarchesoti.workshopmongo.domain.Post;
import dev.lmarchesoti.workshopmongo.repository.PostRepository;
import dev.lmarchesoti.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post not found with id " + id));
    }
}
