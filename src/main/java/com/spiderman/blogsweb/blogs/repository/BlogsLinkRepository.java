package com.spiderman.blogsweb.blogs.repository;


import com.spiderman.blogsweb.blogs.entity.BlogsLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogsLinkRepository extends JpaRepository<BlogsLinkEntity,String>, JpaSpecificationExecutor<BlogsLinkEntity> {

    @Query("select s.id as id,s.sketch as sketch from BlogsLinkEntity s where s.id in :ids")
    List<Map<String,String>> querySketchByIds(@Param("ids") List<String> ids);
}
