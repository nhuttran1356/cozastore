package com.cybersoft.cozastore_java21.repository;

import com.cybersoft.cozastore_java21.entity.BlogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BlogRepository extends JpaRepository<BlogEntity,Integer> {
    @Query("select b from blog b")
    List<BlogEntity> getAll();

    @Query("select b FROM blog b")
    Page<BlogEntity> getBlogPaging(Pageable pageable);

}
