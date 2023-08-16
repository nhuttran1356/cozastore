package com.cybersoft.cozastore_java21.service;

import com.cybersoft.cozastore_java21.entity.ColorEntity;
import com.cybersoft.cozastore_java21.entity.ProductEntity;
import com.cybersoft.cozastore_java21.payload.response.ColorResponse;
import com.cybersoft.cozastore_java21.payload.response.ProductResponse;
import com.cybersoft.cozastore_java21.repository.ColorRepository;
import com.cybersoft.cozastore_java21.service.imp.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class ColorService implements ColorServiceImp{

    @Autowired
    private ColorRepository colorRepository;
    @Override
    public List<ColorResponse> getAllColor() {
        List<ColorEntity> list = colorRepository.findAll();
        List<ColorResponse> responseList = new ArrayList<>();
        for (ColorEntity data : list) {
            ColorResponse colorResponse = new ColorResponse();
            colorResponse.setId(data.getId());
            colorResponse.setName(data.getName());

            responseList.add(colorResponse);
        }
        return responseList;


    }
}
