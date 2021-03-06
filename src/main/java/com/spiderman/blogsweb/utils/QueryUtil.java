package com.spiderman.blogsweb.utils;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;

public class QueryUtil {

    public static Query create(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return query;
    }

    /**
     * 分页查询
     * 基础条件 + 排序查询
     * @param where 基础条件 Map
     * @param limit 分页条件 limit 丢弃limit往后的数据
     * @param skip 分页条件 skip 截取skip往后的数据
     * @param stors 排序条件 List
     * @return 查询Query
     */
    public static Query create(Map<String,Object> where,int limit,int skip, List<Sort.Order> stors){
        Query query = create(where,stors);
        query.skip(skip).limit(limit);
        return query;
    }

    /**
     * 基础条件 + 排序查询
     * @param where 基础条件 Map
     * @param stors 排序条件 List
     * @return 查询Query
     */
    public static Query create(Map<String,Object> where, List<Sort.Order> stors){
        Query query = create(where);
        query.with(Sort.by(stors));
        return query;
    }

    /**
     * 基础条件查询
     * @param where 查询条件 Map
     * @return 查询Query
     */
    public static Query create(Map<String,Object> where){
        Criteria cwhere = null;
        for (String key: where.keySet()) {
            if (cwhere == null){
                cwhere = Criteria.where(key).is(where.get(key));
            }else {
                cwhere.and(key).is(where.get(key));
            }
        }
        assert cwhere != null;
        return new Query(cwhere);
    }
}
