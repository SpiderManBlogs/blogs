package com.spiderman.blogsweb.classification.service;

import com.spiderman.blogsweb.classification.model.ClassificationModel;
import com.spiderman.blogsweb.utils.CheckoutException;

public interface ClassificationService {

    /**
     * 新增分类
     * @param classificationModel 分类model
     * @return 保存后分类Model
     */
    ClassificationModel add(ClassificationModel classificationModel) throws CheckoutException;

    /**
     * 更新分类
     * @param classificationModel 分类model
     * @return 更新后分类Model
     */
    ClassificationModel update(ClassificationModel classificationModel) throws CheckoutException;

    /**
     * 更新分类启用状态
     * @param id 分类id
     * @param enable 启用状态
     * @return 影响条数 如果更新成功则为1 否则为0
     */
    int updateEnable(String id, boolean enable);

    /**
     * 删除分类 逻辑删除dr=1
     * @param id 分类id
     */
    void delete(String id) throws CheckoutException;
}
