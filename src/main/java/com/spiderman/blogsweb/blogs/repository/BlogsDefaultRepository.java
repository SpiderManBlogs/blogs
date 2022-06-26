package com.spiderman.blogsweb.blogs.repository;

import com.spiderman.blogsweb.blogs.entity.BlogsDefaultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogsDefaultRepository extends JpaRepository<BlogsDefaultEntity,String>, JpaSpecificationExecutor<BlogsDefaultEntity> {

    @Modifying
    @Query("update BlogsDefaultEntity c set c.nextblogid=:id where c.dr = 0 and c.id <>:id and c.nextblogid is null ")
    int setNext(@Param("id")String id);

    @Query("select s.id as id,s.title as title from BlogsDefaultEntity s where s.id in :ids")
    List<Map<String,String>> queryTitleByIds(@Param("ids") List<String> ids);
}
