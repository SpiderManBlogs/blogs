package com.spiderman.blogsweb.blogs.repository;

import com.spiderman.blogsweb.blogs.entity.BlogsDefaultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogsDefaultRepos extends JpaRepository<BlogsDefaultEntity,String>, JpaSpecificationExecutor<BlogsDefaultEntity> {
}
