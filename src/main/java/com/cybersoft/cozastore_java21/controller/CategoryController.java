package com.cybersoft.cozastore_java21.controller;

import com.cybersoft.cozastore_java21.payload.response.BaseResponse;
import com.cybersoft.cozastore_java21.service.CategoryService;
import com.cybersoft.cozastore_java21.service.imp.CategoryServiceImp;
import com.cybersoft.cozastore_java21.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping("/clear-cache")
    @CacheEvict(value = "listCategory", allEntries = true)
    public ResponseEntity<?> clearcache(){

        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCategory() {
        BaseResponse response = new BaseResponse();
        response.setData(categoryServiceImp.getAllCategory());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/product")
    public ResponseEntity<?> getProductByCategoryId(@RequestParam int id){
        BaseResponse response = new BaseResponse();
        response.setData(productServiceImp.getProductByCategory(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
