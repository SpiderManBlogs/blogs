package com.spiderman.blogsweb.blogs.repository;

import com.spiderman.blogsweb.blogs.entity.BlogsListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogsListRepository extends JpaRepository<BlogsListEntity,String>, JpaSpecificationExecutor<BlogsListEntity> {
}
