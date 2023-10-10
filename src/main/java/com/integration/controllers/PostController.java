package com.integration.controllers;

import com.integration.services.PostService;
import com.integration.services.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostServiceImpl postService;

    @GetMapping
    public List<Map<String, Object>> index(){
        return postService.GetAllPosts();
    }

    @GetMapping("/{id}")
    public Map<String, Object> show(@PathVariable int id){
        return postService.GetPostsById(id);
    }

    @PostMapping
    public Map<String, Object> create(@RequestBody Map<String, Object> payload){
        return postService.CreatePost(payload);
    }

    @PutMapping("/{id}")
    public  Map<String, Object> update(@RequestBody Map<String, Object> payload, @PathVariable int id){
        return postService.UpdatePost(payload, id);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> destroy(@PathVariable int id){
        return postService.DestroyPost(id);
    }

}
