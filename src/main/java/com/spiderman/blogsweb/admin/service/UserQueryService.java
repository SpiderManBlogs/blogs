package com.spiderman.blogsweb.admin.service;

import com.spiderman.blogsweb.admin.entity.UserEntity;

public interface UserQueryService {
    /**
     * 登录认证
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    UserEntity login(String username,String password);
}
