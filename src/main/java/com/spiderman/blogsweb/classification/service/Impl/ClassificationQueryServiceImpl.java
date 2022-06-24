package com.spiderman.blogsweb.classification.service.Impl;

import com.spiderman.blogsweb.classification.entity.ClassificationEntity;
import com.spiderman.blogsweb.classification.model.ClassificationModel;
import com.spiderman.blogsweb.classification.repository.ClassificationRepository;
import com.spiderman.blogsweb.classification.service.ClassificationQueryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassificationQueryServiceImpl implements ClassificationQueryService {

    private ClassificationRepository dao;

    @Autowired
    public void setDao(ClassificationRepository dao) {
        this.dao = dao;
    }

    @Override
    public Page<ClassificationEntity> queryAll(ClassificationModel querySearch) {
        return dao.findAll(getSpecification(querySearch), PageRequest.of(querySearch.getCurrent()-1, querySearch.getPageSize()));
    }

    private Specification<ClassificationEntity> getSpecification(ClassificationModel querySearch){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("enable"), querySearch.isEnable()));
            if (StringUtils.hasText(querySearch.getCode())) {
                list.add(criteriaBuilder.like(root.get("code"), "%" + querySearch.getCode() + "%"));
            }
            if (StringUtils.hasText(querySearch.getName())) {
                list.add(criteriaBuilder.like(root.get("name"), "%" + querySearch.getName() + "%"));
            }
            if (StringUtils.hasText(querySearch.getMemo())) {
                list.add(criteriaBuilder.like(root.get("memo"), "%" + querySearch.getMemo() + "%"));
            }
            return query.where(list.toArray(new Predicate[0])).getRestriction();
        };
    }

    @Override
    public List<ClassificationModel> queryAll() {
        List<ClassificationEntity> all = dao.findAll();
        all = all.stream().filter(ClassificationEntity::isEnable).collect(Collectors.toList());
        List<ClassificationModel> back = new ArrayList<>();
        for (ClassificationEntity entity:all) {
            ClassificationModel model = new ClassificationModel();
            BeanUtils.copyProperties(entity,model);
            back.add(model);
        }
        return back;
    }
}
