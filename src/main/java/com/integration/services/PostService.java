package com.integration.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PostService {
    List<Map<String, Object>> GetAllPosts();
    Map<String, Object> GetPostsById(int id);
    Map<String, Object> CreatePost(Map<String, Object> payload);
    Map<String, Object> UpdatePost(Map<String, Object> payload, int id);
    Map<String, Object> DestroyPost(int id);

}
