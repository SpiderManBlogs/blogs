package com.spiderman.blogsweb.serial.repository;

import com.spiderman.blogsweb.serial.entity.SerialNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerialRepository extends JpaRepository<SerialNumberEntity,String>, JpaSpecificationExecutor<SerialNumberEntity> {

    @Query("select c from SerialNumberEntity c where c.date=:date")
    List<SerialNumberEntity> queryByDate(@Param("date") String date);

    @Query("update SerialNumberEntity c set c.num=c.num+1 where c.id=:id")
    int updateNumByDate(@Param("id") String id);
}
