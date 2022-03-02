package com.spiderman.blogs.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.spiderman.blogs.entity.BlogsDefaultEntity;
import com.spiderman.blogs.entity.BlogsListEntity;
import com.spiderman.blogs.service.BlogsQueryService;
import com.spiderman.blogs.vo.BlogsListVO;
import com.spiderman.defdoc.service.DefdocQueryService;
import com.spiderman.utils.GlobalStatic;
import com.spiderman.utils.QueryNullException;
import com.spiderman.utils.QueryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BlogsQueryServiceImpl implements BlogsQueryService {

    private static final Logger log = LogManager.getLogger(BlogsQueryServiceImpl.class.getName());

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
            vo.setBlogid(backentity.getBlogid().toString());
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
            ObjectId next = ((BlogsDefaultEntity)byId).getNext();
            Query query = new Query();
            // id 是 当前的next 或者 next 是 当前的id
            Criteria criteriaprev = Criteria.where("next").is(new ObjectId(id));
            Criteria criterianext = Criteria.where("_id").is(next);
            if (next == null){
                query.addCriteria(criteriaprev);
            }else{
                query.addCriteria(new Criteria().orOperator(criteriaprev,criterianext));
            }
            List<BlogsDefaultEntity> blogsDefaultEntities = mongoTemplate.find(query, BlogsDefaultEntity.class);
            Map<String, JSONObject> collect = blogsDefaultEntities.stream()
                    .map(item -> {
                        JSONObject jsonNext = new JSONObject();
                        jsonNext.put("id", item.getId());
                        jsonNext.put("title", item.getTitle());
                        return jsonNext;
                    }).collect(Collectors.toMap(item -> item.getString("id").equals(next == null ? "null" : next.toString()) ? "next" : "prev", item -> item));
            backJson.put("prev",collect.get("prev"));
            backJson.put("next",collect.get("next"));

            return backJson;
        }
        return byId;
    }
}
