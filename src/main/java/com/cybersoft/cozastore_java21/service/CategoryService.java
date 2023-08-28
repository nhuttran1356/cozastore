package com.cybersoft.cozastore_java21.service;

import com.cybersoft.cozastore_java21.entity.CategoryEntity;
import com.cybersoft.cozastore_java21.payload.response.CategoryResponse;
import com.cybersoft.cozastore_java21.repository.CategoryRepository;
import com.cybersoft.cozastore_java21.service.imp.CategoryServiceImp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
//    @Cacheable("listCategory")
    public List<CategoryResponse> getAllCategory() {
//        List<CategoryResponse> responseList = new ArrayList<>();
//        System.out.println("kiem tra category");
//        if (redisTemplate.hasKey("listCategory")) {
//            System.out.println("Co gia tri tren redis");
//            String data = redisTemplate.opsForValue().get("listCategory").toString();
//
//            Type listType = new TypeToken<ArrayList<CategoryResponse>>(){}.getType();
//            responseList = new Gson().fromJson(data, listType);
//        } else {
//            System.out.println("0 gia tri tren redis");
//            List<CategoryEntity> list = categoryRepository.findAll();
//            for (CategoryEntity data : list) {
//                CategoryResponse categoryResponse = new CategoryResponse();
//                categoryResponse.setId(data.getId());
//                categoryResponse.setName(data.getName());
//
//                responseList.add(categoryResponse);
//            }
//            Gson gson = new Gson();
//            String data = gson.toJson(responseList);
//            redisTemplate.opsForValue().set("listCategory", data);
//        }
//        return responseList;
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        List<CategoryEntity> categoryEntityList =categoryRepository.findAll();
        for (CategoryEntity data : categoryEntityList) {

            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setName(data.getName());
            categoryResponse.setId(data.getId());
            categoryResponseList.add(categoryResponse);
        }
        return categoryResponseList;
    }
}
