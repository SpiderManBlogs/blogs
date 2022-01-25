package com.spiderman.utils;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Map;

public class QueryUtil {

    public static Query create(Map<String,Object> where){
        Criteria cwhere = null;
        for (String key: where.keySet()) {
            Criteria criteria = Criteria.where(key).is(where.get(key));
            if (cwhere == null){
                cwhere = criteria;
            }else {
                cwhere.andOperator(criteria);
            }
        }
        assert cwhere != null;
        return new Query(cwhere);
    }
}
