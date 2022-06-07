package com.spiderman.blogsweb.admin.controller;

import com.spiderman.blogsweb.admin.entity.UserEntity;
import com.spiderman.blogsweb.admin.model.Result;
import com.spiderman.blogsweb.admin.model.UserModel;
import com.spiderman.blogsweb.admin.service.UserQueryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AdminLoginController {

    private static final Logger log = LogManager.getLogger(AdminLoginController.class.getName());

    @Autowired
    private UserQueryService userQueryService;

    @RequestMapping("/account")
    @ResponseBody
    public Result login(@RequestBody UserModel userModel) {
        Result result = Result.instance();
        try {
            UserEntity userEntity = userQueryService.login(userModel.getUsername(),userModel.getPassword());
            if (userEntity != null && StringUtils.hasText(userEntity.getCode())){
                result.success();
                result.put("type",userModel.getType());
                result.put("currentAuthority",userEntity.getCurrentAuthority());
            }else {
                result.fail();
                result.setErrorMessage("认证错误");
                result.put("type",userModel.getType());
            }
        }catch (Exception e){
            result.fail();
            result.setErrorCode("检查失败：" + e.getMessage());
            result.put("type",userModel.getType());
            log.error("检查失败：" + e.getMessage());
        }
        return result;
    }

}
