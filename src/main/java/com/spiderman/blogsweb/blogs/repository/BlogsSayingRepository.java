package com.spiderman.blogsweb.blogs.repository;

import com.spiderman.blogsweb.blogs.entity.BlogsSayingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogsSayingRepository extends JpaRepository<BlogsSayingEntity,String>, JpaSpecificationExecutor<BlogsSayingEntity> {

    @Query("select s.id as id,s.saying as saying from BlogsSayingEntity s where s.id in :ids")
    List<Map<String,String>> querySayingByIds(@Param("ids")List<String> ids);
}
