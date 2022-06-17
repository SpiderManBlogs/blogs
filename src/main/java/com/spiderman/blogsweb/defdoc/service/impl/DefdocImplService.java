package com.spiderman.blogsweb.defdoc.service.impl;

import com.spiderman.blogsweb.defdoc.entity.DefdocEntity;
import com.spiderman.blogsweb.defdoc.entity.DefdocListEntity;
import com.spiderman.blogsweb.defdoc.service.DefdocService;
import com.spiderman.blogsweb.defdoc.vo.DefdocListVO;
import com.spiderman.blogsweb.defdoc.vo.DefdocVO;
import com.spiderman.blogsweb.utils.CheckoutException;
import com.spiderman.blogsweb.utils.QueryNullException;
import com.spiderman.blogsweb.utils.QueryUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<DefdocVO> add(DefdocVO... defdocs) throws CheckoutException {
        List<DefdocVO> backs = new ArrayList<>();
        List<DefdocEntity> saveEntitys= new ArrayList<>();
        int length = Arrays.stream(defdocs).map(DefdocVO::getDefdoccode).toArray().length;
        int length2 = Arrays.stream(defdocs).map(DefdocVO::getDefdoccode).distinct().toArray().length;
        if (length != length2){
            throw new CheckoutException("档案编码不能重复。");
        }
        for (DefdocVO defdoc:defdocs) {
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
            saveEntitys.add(entity);
        }
        saveEntitys.forEach(entity -> {
            DefdocEntity save = mongoTemplate.save(entity);
            DefdocVO backvo = new DefdocVO();
            BeanUtils.copyProperties(save,backvo);
            backs.add(backvo);
        });
        return backs;
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
    public List<DefdocVO> edit(DefdocVO... defdocs) throws QueryNullException {
        for (DefdocVO defdoc:defdocs) {
            DefdocEntity byId = mongoTemplate.findById(defdoc.getDefdocid(), DefdocEntity.class);
            if(byId == null){
                throw new QueryNullException("id查询为null:" + defdoc.getDefdocid());
            }
            byId.setDr(1);
            Update update = new Update();
            update.set("defdocname",defdoc.getDefdocname());
            update.set("enablement",defdoc.getEnablement());
            mongoTemplate.upsert(QueryUtil.create(defdoc.getDefdocid()), update, DefdocEntity.class);
        }
        return Arrays.asList(defdocs);
    }
}
