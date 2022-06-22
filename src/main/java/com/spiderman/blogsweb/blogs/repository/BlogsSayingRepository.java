package com.spiderman.blogsweb.blogs.repository;

import com.spiderman.blogsweb.blogs.entity.BlogsSayingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogsSayingRepository extends JpaRepository<BlogsSayingEntity,String>, JpaSpecificationExecutor<BlogsSayingEntity> {
}
