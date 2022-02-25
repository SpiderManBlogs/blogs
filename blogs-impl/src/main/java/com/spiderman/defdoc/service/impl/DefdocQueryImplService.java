package com.spiderman.defdoc.service.impl;

import com.spiderman.defdoc.entity.DefdocEntity;
import com.spiderman.defdoc.entity.DefdocListEntity;
import com.spiderman.defdoc.service.DefdocQueryService;
import com.spiderman.defdoc.vo.DefdocListVO;
import com.spiderman.defdoc.vo.DefdocVO;
import com.spiderman.utils.QueryUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DefdocQueryImplService implements DefdocQueryService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<DefdocListVO> query() {
        Map<String, Object> where = new HashMap<>();
        where.put("dr",0);
//        where.put("enablement",1);
        List<DefdocListEntity> backentity = mongoTemplate.find(QueryUtil.create(where), DefdocListEntity.class);
        List<DefdocListVO> backvos = new ArrayList<>();
        for (DefdocListEntity entity:backentity) {
            DefdocListVO vo = new DefdocListVO();
            BeanUtils.copyProperties(entity,vo);
            backvos.add(vo);
        }
        return backvos;
    }

    @Override
    public List<DefdocVO> query(String id) {
        Map<String, Object> where = new HashMap<>();
        where.put("dr",0);
//        where.put("enablement",1);
        where.put("defdoclistid",id);
        List<DefdocEntity> backentity = mongoTemplate.find(QueryUtil.create(where), DefdocEntity.class);
        List<DefdocVO> backvos = new ArrayList<>();
        for (DefdocEntity entity:backentity) {
            DefdocVO vo = new DefdocVO();
            BeanUtils.copyProperties(entity,vo);
            backvos.add(vo);
        }
        return backvos;
    }

    @Override
    public DefdocVO queryDefDocByid(String id) {
        DefdocEntity byId = mongoTemplate.findById(id, DefdocEntity.class);
        if(byId != null){
            DefdocVO vo = new DefdocVO();
            BeanUtils.copyProperties(byId,vo);
            return vo;
        }else{
            return null;
        }
    }
}
