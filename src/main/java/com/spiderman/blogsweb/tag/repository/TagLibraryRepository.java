package com.spiderman.blogsweb.tag.repository;

import com.spiderman.blogsweb.tag.entity.TagLibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagLibraryRepository extends JpaRepository<TagLibraryEntity,String>, JpaSpecificationExecutor<TagLibraryEntity> {

    @Query("select tags from TagLibraryEntity tags where tags.dr=0 and tags.name in :names")
    List<TagLibraryEntity>queryByNames(@Param("names")List<String> names);
}
