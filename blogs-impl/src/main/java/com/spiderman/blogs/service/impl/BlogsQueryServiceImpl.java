package com.spiderman.blogs.service.impl;

import com.spiderman.blogs.entity.BlogsLinkEntity;
import com.spiderman.blogs.entity.BlogsListEntity;
import com.spiderman.blogs.entity.BlogsSayingEntity;
import com.spiderman.blogs.service.BlogsQueryService;
import com.spiderman.blogs.vo.BlogsListVO;
import com.spiderman.utils.GlobalStatic;
import com.spiderman.utils.QueryUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogsQueryServiceImpl implements BlogsQueryService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<BlogsListVO> queryPage(int pageSize, int pageThis) {

        Map<String, Object> where = new HashMap<>();
        where.put("dr",0);
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(Sort.Order.desc("order"));
        List<BlogsListEntity> backEntitys = mongoTemplate.find(QueryUtil.create(where,pageSize * pageThis,pageSize, orders), BlogsListEntity.class);
        List<BlogsListVO> backVOS = new ArrayList<>();
        for (BlogsListEntity backentity:backEntitys) {
            BlogsListVO vo = new BlogsListVO();
            BeanUtils.copyProperties(backentity,vo);
            backVOS.add(vo);
        }
        return backVOS;
    }

    @Override
    public long queryCount() {
        Map<String, Object> where = new HashMap<>();
        where.put("dr",0);
        return mongoTemplate.count(QueryUtil.create(where), BlogsListEntity.class);
    }

    @Override
    public Object queryById(String id, String type) throws Exception {
        return mongoTemplate.findById(new ObjectId(id),GlobalStatic.getClass(type));
    }
}
