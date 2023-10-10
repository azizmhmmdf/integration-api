package com.integration.services.impl;

import com.integration.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    String baseURL = "https://jsonplaceholder.typicode.com";
    StringBuilder stringBuilder = new StringBuilder(baseURL);
    String pathUrl = "/posts/";


    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<Map<String, Object>> GetAllPosts() {
        try{
            HttpEntity <Void> httpEntity  = new HttpEntity<>(httpHeaders());
            // funsgi HttpEntity untuk membungkus data yng akan dikirim dalam permintaan HTTP seperti json
            // http ini result nya value headers itu sendiri yaitu [Accept:"application/json", Content-Type:"application/json"]

            String url = stringBuilder.append(pathUrl).toString();
            // stringBuilder untuk menggabungkan antara baseURL yang sudah diset sebelum nya dengan pathURL
            // result dari variabel url ini : https://jsonplaceholder.typicode.com/posts/

            ResponseEntity<List> response  =  restTemplate.exchange(url, HttpMethod.GET, httpEntity, List.class);
            // ResponseEntity kegunaanya untuk mengambil data response
            // restTemplate kegunaannya untuk berinteraksi dengan protocol HTTP
            // List.class ini untuk menentukan type responsenya

            return response.getBody();
        }catch (HttpClientErrorException.NotFound e){
            return Collections.emptyList();
        }
    }

    @Override
    public Map<String, Object> GetPostsById(int id) {
        try {
            HttpEntity <Void> httpEntity  = new HttpEntity<>(httpHeaders());
            String url = stringBuilder.append(pathUrl).append(id).toString();
            ResponseEntity<Map> response  =  restTemplate.exchange(url, HttpMethod.GET, httpEntity, Map.class);
            return (Map<String, Object>) response.getBody();
        }catch (HttpClientErrorException.NotFound e){
            return Collections.emptyMap();
        }
    }

    @Override
    public Map<String, Object> CreatePost(Map<String, Object> payload) {
        HttpEntity<Map> httpEntity = new HttpEntity<>(payload, httpHeaders());
        String url = stringBuilder.append(pathUrl).toString();
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> UpdatePost(Map<String, Object> payload, int id) {
        HttpEntity<Map> httpEntity = new HttpEntity<>(payload, httpHeaders());
        String url = stringBuilder.append(pathUrl).append(id).toString();
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Map.class);
        return response.getBody();
    }

    @Override
    public Map<String, Object> DestroyPost(int id) {
        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders());
        String url = stringBuilder.append(pathUrl).append(id).toString();
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Map.class);
        return response.getBody();
    }


    public HttpHeaders httpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        // disini kita membuat object kosong untuk menampung headers

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        // disini kita set Accept nya dengan value application_json
        headers.setContentType(MediaType.APPLICATION_JSON);
        // disini kita set juga content type nya dengan value application_json
        return headers;
        // jadi result headers yang sebelum nya kita set sepeti ini jadi nya : [Accept:"application/json", Content-Type:"application/json"]
    }
}
