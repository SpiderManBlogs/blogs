package com.spiderman.blogsweb.blogs.service.impl;

import com.spiderman.blogsweb.blogs.converter.BlogType;
import com.spiderman.blogsweb.blogs.entity.BlogsListEntity;
import com.spiderman.blogsweb.blogs.repository.BlogsDefaultRepository;
import com.spiderman.blogsweb.blogs.repository.BlogsLinkRepository;
import com.spiderman.blogsweb.blogs.repository.BlogsListRepository;
import com.spiderman.blogsweb.blogs.repository.BlogsSayingRepository;
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

    private BlogsSayingRepository sayingDao;
    private BlogsLinkRepository linkDao;
    private BlogsDefaultRepository defaultDao;

    @Autowired
    public void setSayingDao(BlogsSayingRepository sayingDao) {
        this.sayingDao = sayingDao;
    }

    @Autowired
    public void setLinkDao(BlogsLinkRepository linkDao) {
        this.linkDao = linkDao;
    }

    @Autowired
    public void setDefaultDao(BlogsDefaultRepository defaultDao) {
        this.defaultDao = defaultDao;
    }

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
            if (querySearch.getBlogtype() != null) {
                list.add(criteriaBuilder.equal(root.get("blogtype"),querySearch.getBlogtype()));
            }
            return query.where(list.toArray(new Predicate[0])).getRestriction();
        };
    }

    @Override
    public Object query(String id, BlogType type) throws Exception {
        Object back;
        switch (type){
            case SAYING:
                back = sayingDao.findById(id);
                break;
            case LINK:
                back = linkDao.findById(id);
                break;
            case IMAGE:
            case IMAGES:
            case VIDEO:
            case AUDIO:
                back = defaultDao.findById(id);
                break;
            default:
                throw new Exception("查询类型不正确!");
        }
        return back;
    }
}
