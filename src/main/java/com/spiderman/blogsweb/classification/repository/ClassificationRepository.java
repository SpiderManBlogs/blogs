package com.spiderman.blogsweb.classification.repository;

import com.spiderman.blogsweb.classification.entity.ClassificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassificationRepository extends JpaRepository<ClassificationEntity,String>, JpaSpecificationExecutor<ClassificationEntity> {

    @Query("select c from ClassificationEntity c where c.dr=0 and c.code=:code")
    List<ClassificationEntity> findByCode(@Param("code") String code);

    @Modifying
    @Query("update ClassificationEntity c set c.enable=:enable where c.id=:id")
    int updateEnableById(@Param("id") String id,@Param("enable")boolean enable);
}
