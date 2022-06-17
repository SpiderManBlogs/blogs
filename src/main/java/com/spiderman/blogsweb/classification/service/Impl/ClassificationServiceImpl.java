package com.spiderman.blogsweb.classification.service.Impl;

import com.spiderman.blogsweb.classification.entity.ClassificationEntity;
import com.spiderman.blogsweb.classification.model.ClassificationModel;
import com.spiderman.blogsweb.classification.repository.ClassificationRepository;
import com.spiderman.blogsweb.classification.service.ClassificationService;
import com.spiderman.blogsweb.utils.CheckoutException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClassificationServiceImpl implements ClassificationService {

    private ClassificationRepository dao;

    @Autowired
    public void setDao(ClassificationRepository dao) {
        this.dao = dao;
    }

    @Override
    public ClassificationModel add(ClassificationModel classificationModel) throws CheckoutException {
        ClassificationEntity entity = new ClassificationEntity();
        BeanUtils.copyProperties(classificationModel,entity);
        //检验code是否重复
        List<ClassificationEntity> byCode = dao.findByCode(entity.getCode());
        if (byCode.size() > 0){
            throw new CheckoutException(entity.getCode() + "编码已存在。");
        }
        ClassificationEntity save = dao.save(entity);
        ClassificationModel back = new ClassificationModel();
        BeanUtils.copyProperties(save,back);
        return back;
    }

    @Override
    public ClassificationModel update(ClassificationModel classificationModel) throws CheckoutException {
        Optional<ClassificationEntity> byId = dao.findById(classificationModel.getId());
        if (!byId.isPresent()){
            throw new CheckoutException("查询数据不存在。");
        }
        ClassificationEntity entity = byId.get();
        entity.setName(classificationModel.getName());
        entity.setMemo(classificationModel.getMemo());
        ClassificationEntity save = dao.save(entity);
        ClassificationModel back = new ClassificationModel();
        BeanUtils.copyProperties(save,back);
        return back;
    }

    @Override
    public int updateEnable(String id, boolean enable) {
        return dao.updateEnableById(id,enable);
    }

    @Override
    public void delete(String id) throws CheckoutException {
        Optional<ClassificationEntity> byId = dao.findById(id);
        if (!byId.isPresent()){
            throw new CheckoutException("查询数据不存在。");
        }
        ClassificationEntity entity = byId.get();
        if (entity.isEnable()){
            throw new CheckoutException("分类启用状态不能删除。");
        }else{
            dao.deleteById(id);
        }
    }
}
