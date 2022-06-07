package com.spiderman.blogsweb.admin.service.impl;

import com.google.common.collect.ImmutableMap;
import com.spiderman.blogsweb.admin.entity.UserEntity;
import com.spiderman.blogsweb.admin.service.UserQueryService;
import com.spiderman.blogsweb.utils.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UserEntity login(String username, String password) {
        Query query = QueryUtil.create(new ImmutableMap.Builder<String, Object>()
                .put("dr",0)
                .put("code", username)
                .put("password", password)
                .build());
        return mongoTemplate.findOne(query, UserEntity.class);
    }
}
