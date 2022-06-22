package com.spiderman.blogsweb.blogs.service.impl;

import com.spiderman.blogsweb.blogs.entity.BlogsListEntity;
import com.spiderman.blogsweb.blogs.repository.BlogsListRepository;
import com.spiderman.blogsweb.blogs.service.BlogsQueryService;
import com.spiderman.blogsweb.blogs.vo.BlogsListVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogsQueryServiceImpl implements BlogsQueryService {

    private static final Logger log = LogManager.getLogger(BlogsQueryServiceImpl.class.getName());

    private BlogsListRepository listDao;

    @Autowired
    public void setListDao(BlogsListRepository listDao) {
        this.listDao = listDao;
    }

    @Override
    public Page<BlogsListEntity> queryAll(BlogsListVO querySearch) {
        return listDao.findAll(getBlogsListSpecification(querySearch), PageRequest.of(querySearch.getCurrent()-1, querySearch.getPageSize()));
    }

    private Specification<BlogsListEntity> getBlogsListSpecification(BlogsListVO querySearch){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.hasText(querySearch.getCode())) {
                list.add(criteriaBuilder.like(root.get("code"), "%" + querySearch.getCode() + "%"));
            }
            if (StringUtils.hasText(querySearch.getBlogtype().value())) {
                list.add(criteriaBuilder.equal(root.get("blogtype"),querySearch.getBlogtype().value()));
            }
            return query.where(list.toArray(new Predicate[0])).getRestriction();
        };
    }
}
