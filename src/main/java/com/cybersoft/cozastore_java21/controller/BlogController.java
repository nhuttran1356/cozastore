package com.cybersoft.cozastore_java21.controller;

import com.cybersoft.cozastore_java21.service.imp.BlogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogServiceImp blogServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllAndPagingBlog(@RequestParam HashMap<String,String> hashMap){
        int page = Integer.parseInt(hashMap.getOrDefault("page","0"));
        int size = Integer.parseInt(hashMap.getOrDefault("size","3"));

        return new ResponseEntity<>(blogServiceImp.getAllBlog(page, size), HttpStatus.OK);
    }
}
