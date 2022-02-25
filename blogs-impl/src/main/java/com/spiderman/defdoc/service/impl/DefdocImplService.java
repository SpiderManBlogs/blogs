package com.spiderman.defdoc.service.impl;

import com.spiderman.defdoc.entity.DefdocEntity;
import com.spiderman.defdoc.entity.DefdocListEntity;
import com.spiderman.defdoc.service.DefdocService;
import com.spiderman.defdoc.vo.DefdocListVO;
import com.spiderman.defdoc.vo.DefdocVO;
import com.spiderman.utils.CheckoutException;
import com.spiderman.utils.QueryNullException;
import com.spiderman.utils.QueryUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DefdocImplService implements DefdocService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public DefdocListVO add(DefdocListVO defdoc) throws CheckoutException {
        DefdocListEntity entity = new DefdocListEntity();
        BeanUtils.copyProperties(defdoc,entity);
        Map<String, Object> where = new HashMap<>();
        where.put("dr",0);
        where.put("defdoclistcode",entity.getDefdoclistcode());
        long count = mongoTemplate.count(QueryUtil.create(where), DefdocListEntity.class);
        if (count > 0){
            throw new CheckoutException(entity.getDefdoclistcode() + "档案编码已存在。");
        }
        entity.setEnablement(1);
        entity.setDr(0);
        DefdocListEntity save = mongoTemplate.save(entity);
        DefdocListVO backvo = new DefdocListVO();
        BeanUtils.copyProperties(save,backvo);
        return backvo;
    }

    @Override
    public DefdocVO add(DefdocVO defdoc) throws CheckoutException {
        DefdocEntity entity = new DefdocEntity();
        BeanUtils.copyProperties(defdoc,entity);
        Map<String, Object> where = new HashMap<>();
        where.put("dr",0);
        where.put("defdoccode",entity.getDefdoccode());
        where.put("defdoclistid",entity.getDefdoclistid());
        long count = mongoTemplate.count(QueryUtil.create(where), DefdocEntity.class);
        if (count > 0){
            throw new CheckoutException(entity.getDefdoccode() + "档案编码已存在。");
        }
        entity.setEnablement(1);
        entity.setDr(0);
        DefdocEntity save = mongoTemplate.save(entity);
        DefdocVO backvo = new DefdocVO();
        BeanUtils.copyProperties(save,backvo);
        return backvo;
    }

    @Override
    public void delete(DefdocListVO defdocList) throws QueryNullException {
        DefdocListEntity byId = mongoTemplate.findById(defdocList.getDefdoclistid(), DefdocListEntity.class);
        if(byId == null){
            throw new QueryNullException("id查询为null:" + defdocList.getDefdoclistid());
        }
        byId.setDr(1);
        Update update = new Update();
        update.set("dr",1);
        mongoTemplate.upsert(QueryUtil.create(defdocList.getDefdoclistid()), update, DefdocListEntity.class);
    }

    @Override
    public void delete(DefdocVO defdoc) throws QueryNullException {
        DefdocEntity byId = mongoTemplate.findById(defdoc.getDefdocid(), DefdocEntity.class);
        if(byId == null){
            throw new QueryNullException("id查询为null:" + defdoc.getDefdocid());
        }
        byId.setDr(1);
        Update update = new Update();
        update.set("dr",1);
        mongoTemplate.upsert(QueryUtil.create(defdoc.getDefdocid()), update, DefdocEntity.class);
    }

    @Override
    public DefdocListVO edit(DefdocListVO defdocList) throws QueryNullException {
        DefdocListEntity byId = mongoTemplate.findById(defdocList.getDefdoclistid(), DefdocListEntity.class);
        if(byId == null){
            throw new QueryNullException("id查询为null:" + defdocList.getDefdoclistid());
        }
        byId.setDr(1);
        Update update = new Update();
        update.set("defdoclistname",defdocList.getDefdoclistname());
        update.set("enablement",defdocList.getEnablement());
        mongoTemplate.upsert(QueryUtil.create(defdocList.getDefdoclistid()), update, DefdocListEntity.class);
        return defdocList;
    }

    @Override
    public DefdocVO edit(DefdocVO defdoc) throws QueryNullException {
        DefdocEntity byId = mongoTemplate.findById(defdoc.getDefdocid(), DefdocEntity.class);
        if(byId == null){
            throw new QueryNullException("id查询为null:" + defdoc.getDefdocid());
        }
        byId.setDr(1);
        Update update = new Update();
        update.set("defdocname",defdoc.getDefdocname());
        update.set("enablement",defdoc.getEnablement());
        mongoTemplate.upsert(QueryUtil.create(defdoc.getDefdocid()), update, DefdocEntity.class);
        return defdoc;
    }
}
