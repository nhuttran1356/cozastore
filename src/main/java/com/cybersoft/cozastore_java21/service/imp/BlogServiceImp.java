package com.cybersoft.cozastore_java21.service.imp;

import com.cybersoft.cozastore_java21.payload.response.PagingDTO;

public interface BlogServiceImp {
    PagingDTO getAllBlog(int page, int size);
}
