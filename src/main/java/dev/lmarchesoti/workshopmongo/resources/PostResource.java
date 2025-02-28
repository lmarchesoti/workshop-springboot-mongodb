package dev.lmarchesoti.workshopmongo.resources;

import dev.lmarchesoti.workshopmongo.domain.Post;
import dev.lmarchesoti.workshopmongo.resources.util.URL;
import dev.lmarchesoti.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findPost(@PathVariable("id") String id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findPostByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        String titlesearch = URL.decodeParam(text);
        List<Post> posts = postService.findByTitle(titlesearch);
        return ResponseEntity.ok().body(posts);
    }
}
