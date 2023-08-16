package com.cybersoft.cozastore_java21.service.imp;

import com.cybersoft.cozastore_java21.entity.ColorEntity;
import com.cybersoft.cozastore_java21.payload.response.ColorResponse;

import java.util.List;

public interface ColorServiceImp {
    List<ColorResponse> getAllColor();
}
