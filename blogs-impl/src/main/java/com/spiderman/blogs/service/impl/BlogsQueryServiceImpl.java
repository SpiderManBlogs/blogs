package com.spiderman.blogs.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.spiderman.blogs.entity.BlogsDefaultEntity;
import com.spiderman.blogs.entity.BlogsListEntity;
import com.spiderman.blogs.service.BlogsQueryService;
import com.spiderman.blogs.vo.BlogsListVO;
import com.spiderman.defdoc.service.DefdocQueryService;
import com.spiderman.defdoc.vo.DefdocVO;
import com.spiderman.utils.GlobalStatic;
import com.spiderman.utils.QueryNullException;
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
    @Autowired
    private DefdocQueryService defdocQueryService;

    @Override
    public List<BlogsListVO> queryPage(int pageSize, int pageThis) {

        Map<String, Object> where = new HashMap<>();
        where.put("dr",0);
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(Sort.Order.desc("order"));
        List<BlogsListEntity> backEntitys = mongoTemplate.find(QueryUtil.create(where,pageSize,pageSize * pageThis, orders), BlogsListEntity.class);
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
        Object byId = mongoTemplate.findById(new ObjectId(id), GlobalStatic.getClass(type));
        if(GlobalStatic.isCardType(type)){
            String jsonstr = JSON.toJSONStringWithDateFormat(byId, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
            JSONObject backJson = JSONObject.parseObject(jsonstr);
            if (backJson == null){
                throw new QueryNullException("查询失败，博客id不存在。");
            }
            DefdocVO classify = defdocQueryService.queryDefDocByid(backJson.getString("classify"));
            if (classify == null){
                throw new QueryNullException("查询分类失败，分类id不存在。");
            }
            JSONObject classifyjsonObject = new JSONObject();
            classifyjsonObject.put("id",classify.getDefdocid());
            classifyjsonObject.put("name",classify.getDefdocname());
            classifyjsonObject.put("code",classify.getDefdoccode());
            backJson.put("classify",classifyjsonObject);
            return backJson;
        }
        return byId;
    }
}
