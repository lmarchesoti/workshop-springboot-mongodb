package dev.lmarchesoti.workshopmongo.services;

import dev.lmarchesoti.workshopmongo.domain.Post;
import dev.lmarchesoti.workshopmongo.repository.PostRepository;
import dev.lmarchesoti.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post not found with id " + id));
    }

    public List<Post> findByTitle(String text) {
        return postRepository.searchByTitle(text);
    }
}
