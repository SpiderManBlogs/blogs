package com.spiderman.blogs.service.impl;

import com.spiderman.blogs.entity.BlogsListEntity;
import com.spiderman.blogs.entity.BlogsSayingEntity;
import com.spiderman.blogs.service.BlogsSaveService;
import com.spiderman.blogs.vo.BlogsSayingVO;
import com.spiderman.utils.QueryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class BLogsSaveServiceImpl implements BlogsSaveService {

    private static final Logger log = LogManager.getLogger(BLogsSaveServiceImpl.class.getName());

    @Value("${blogs.create}")
    private String create;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public BlogsSayingVO saveSaying(BlogsSayingVO vo) {
        BlogsSayingEntity entity = new BlogsSayingEntity();
        BeanUtils.copyProperties(vo,entity);
        entity.setCreate(create);
        BlogsSayingEntity backEntity = mongoTemplate.save(entity);
        BlogsListEntity listEntity = new BlogsListEntity();
        Map<String,Object> where = new HashMap<>();
        where.put("dr",0);
        long count = mongoTemplate.count(QueryUtil.create(where), BlogsListEntity.class);
        listEntity.setBlogid(backEntity.getId());
        listEntity.setOrder(count+1);
        mongoTemplate.save(listEntity);
        BlogsSayingVO backVO = new BlogsSayingVO();
        BeanUtils.copyProperties(backEntity,backVO);
        return backVO;
    }
}
