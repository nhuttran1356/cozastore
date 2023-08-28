package com.cybersoft.cozastore_java21.service;

import com.cybersoft.cozastore_java21.entity.BlogEntity;
import com.cybersoft.cozastore_java21.payload.response.PagingDTO;
import com.cybersoft.cozastore_java21.repository.BlogRepository;
import com.cybersoft.cozastore_java21.service.imp.BlogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogService implements BlogServiceImp {
    @Autowired
    BlogRepository blogRepository;
    @Override
    public PagingDTO getAllBlog(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<BlogEntity> blogEntities = blogRepository.getBlogPaging(pageable);
        List<?> blogList = blogEntities.toList();
        return new PagingDTO(blogList,
                blogEntities.isLast(), blogEntities.getTotalPages(), blogEntities.getNumber());
    }
}
