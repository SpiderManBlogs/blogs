package com.spiderman.blogs.service.impl;

import com.spiderman.blogs.entity.BlogsLinkEntity;
import com.spiderman.blogs.entity.BlogsListEntity;
import com.spiderman.blogs.entity.BlogsSayingEntity;
import com.spiderman.blogs.service.BlogsSaveService;
import com.spiderman.blogs.vo.BlogsLinkVO;
import com.spiderman.blogs.vo.BlogsSayingVO;
import com.spiderman.utils.GlobalStatic;
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
        BlogsSayingEntity entity = new BlogsSayingEntity(GlobalStatic.TYPE_SAYING);
        BeanUtils.copyProperties(vo,entity);
        entity.setCreate(create);
        BlogsSayingEntity backEntity = mongoTemplate.save(entity);
        //保存列表
        saveList(backEntity.getId(),GlobalStatic.TYPE_SAYING);
        BlogsSayingVO backVO = new BlogsSayingVO();
        BeanUtils.copyProperties(backEntity,backVO);
        return backVO;
    }

    @Override
    public BlogsLinkVO saveLink(BlogsLinkVO vo) {
        BlogsLinkEntity entity = new BlogsLinkEntity(GlobalStatic.TYPE_LINK);
        BeanUtils.copyProperties(vo,entity);
        entity.setCreate(create);
        BlogsLinkEntity backEntity = mongoTemplate.save(entity);
        //保存列表
        saveList(backEntity.getId(),GlobalStatic.TYPE_LINK);
        BlogsLinkVO backVO = new BlogsLinkVO();
        BeanUtils.copyProperties(backEntity,backVO);
        return backVO;
    }

    private void saveList(String blogsid,String type){
        BlogsListEntity listEntity = new BlogsListEntity(type);
        Map<String,Object> where = new HashMap<>();
        where.put("dr",0);
        long count = mongoTemplate.count(QueryUtil.create(where), BlogsListEntity.class);
        listEntity.setBlogid(blogsid);
        listEntity.setOrder(count+1);
        mongoTemplate.save(listEntity);
    }
}
