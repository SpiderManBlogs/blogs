package com.spiderman.blogsweb.blogs.repository;


import com.spiderman.blogsweb.blogs.entity.BlogsLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogsLinkRepository extends JpaRepository<BlogsLinkEntity,String>, JpaSpecificationExecutor<BlogsLinkEntity> {
}
