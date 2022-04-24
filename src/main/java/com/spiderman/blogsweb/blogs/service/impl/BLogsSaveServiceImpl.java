package com.spiderman.blogsweb.blogs.service.impl;

import com.spiderman.blogsweb.blogs.entity.BlogsDefaultEntity;
import com.spiderman.blogsweb.blogs.entity.BlogsLinkEntity;
import com.spiderman.blogsweb.blogs.entity.BlogsListEntity;
import com.spiderman.blogsweb.blogs.entity.BlogsSayingEntity;
import com.spiderman.blogsweb.blogs.service.BlogsSaveService;
import com.spiderman.blogsweb.blogs.vo.BlogsDefaultVO;
import com.spiderman.blogsweb.blogs.vo.BlogsLinkVO;
import com.spiderman.blogsweb.blogs.vo.BlogsSayingVO;
import com.spiderman.blogsweb.defdoc.entity.DefdocEntity;
import com.spiderman.blogsweb.utils.GlobalStatic;
import com.spiderman.blogsweb.utils.QueryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
        saveList(backEntity.getId(), GlobalStatic.TYPE_SAYING);
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

    @Override
    public BlogsDefaultVO save(BlogsDefaultVO vo) {
        BlogsDefaultEntity entity = new BlogsDefaultEntity(vo.getType());
        BeanUtils.copyProperties(vo,entity);
        DefdocEntity defdocEntity = new DefdocEntity();
        BeanUtils.copyProperties(vo.getClassify(),defdocEntity);
        entity.setClassify(defdocEntity);
        entity.setCreate(create);
        entity.setCreateTime(new Date());

        Query query = new Query();
        Criteria criteria = Criteria.where("dr").is(0).and("type").in("image","images","audio","video");
        query.addCriteria(criteria);
        query.with(Sort.by("order").descending());
        query.limit(1);
        List<BlogsListEntity> blogsListEntities = mongoTemplate.find(query, BlogsListEntity.class);
        if (blogsListEntities.size() > 0){
            entity.setNext(blogsListEntities.get(0).getBlogid_obj());
        }

        BlogsDefaultEntity backEntity = mongoTemplate.save(entity);

        //保存列表
        saveList(backEntity.getId(),backEntity.getType());
        BlogsDefaultVO backVO = new BlogsDefaultVO();
        BeanUtils.copyProperties(backEntity,backVO);
        return backVO;
    }

    private void saveList(String blogsid, String type){
        BlogsListEntity listEntity = new BlogsListEntity(type);
        Map<String,Object> where = new HashMap<>();
        where.put("dr",0);
        long count = mongoTemplate.count(QueryUtil.create(where), BlogsListEntity.class);
        listEntity.setBlogid(blogsid);
        listEntity.setOrder(count+1);
        mongoTemplate.save(listEntity);
    }
}
