package com.spiderman.blogsweb.defdoc.service.impl;

import com.spiderman.blogsweb.defdoc.entity.DefdocEntity;
import com.spiderman.blogsweb.defdoc.entity.DefdocListEntity;
import com.spiderman.blogsweb.defdoc.service.DefdocQueryService;
import com.spiderman.blogsweb.defdoc.vo.DefdocListVO;
import com.spiderman.blogsweb.defdoc.vo.DefdocVO;
import com.spiderman.blogsweb.utils.QueryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DefdocQueryImplService implements DefdocQueryService {

    private static final Logger log = LogManager.getLogger(DefdocQueryImplService.class.getName());

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
        where.put("defdoclistid",new ObjectId(id));
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

    @Override
    public List<DefdocVO> queryByCode(String code) {
        LookupOperation lookup = Aggregation.lookup("defdoc", "_id", "defdoclistid", "list");
        Criteria criteria = Criteria.where("dr").is(0).and("defdoclistcode").is(code).and("enablement").is(1);
        MatchOperation match = Aggregation.match(criteria);
        Aggregation agg = Aggregation.newAggregation(lookup,match);
        AggregationResults<Map> studentAggregation = mongoTemplate.aggregate(agg, "defdoclist", Map.class);
        List<Map> mappedResults = studentAggregation.getMappedResults();
        if(mappedResults.size() > 0){
            List<DefdocEntity> list = (List<DefdocEntity>) mappedResults.get(0).get("list");
            return list.stream()
                    .filter(item -> item.getEnablement() == 1 && item.getDr() == 0)
                    .map(item -> {
                        DefdocVO vo = new DefdocVO();
                        BeanUtils.copyProperties(item,vo);
                        return vo;
                    })
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
