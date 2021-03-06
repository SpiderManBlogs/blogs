package com.spiderman.blogsweb.blogs.service.impl;

import com.spiderman.blogsweb.blogs.converter.BlogType;
import com.spiderman.blogsweb.blogs.entity.BlogsDefaultEntity;
import com.spiderman.blogsweb.blogs.entity.BlogsLinkEntity;
import com.spiderman.blogsweb.blogs.entity.BlogsListEntity;
import com.spiderman.blogsweb.blogs.entity.BlogsSayingEntity;
import com.spiderman.blogsweb.blogs.repository.BlogsDefaultRepository;
import com.spiderman.blogsweb.blogs.repository.BlogsLinkRepository;
import com.spiderman.blogsweb.blogs.repository.BlogsListRepository;
import com.spiderman.blogsweb.blogs.repository.BlogsSayingRepository;
import com.spiderman.blogsweb.blogs.service.BlogsQueryService;
import com.spiderman.blogsweb.blogs.vo.*;
import com.spiderman.blogsweb.classification.model.ClassificationModel;
import com.spiderman.blogsweb.tag.model.TagLibraryModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogsQueryServiceImpl implements BlogsQueryService {

    private static final Logger log = LogManager.getLogger(BlogsQueryServiceImpl.class.getName());

    private BlogsListRepository listDao;

    private BlogsSayingRepository sayingDao;
    private BlogsLinkRepository linkDao;
    private BlogsDefaultRepository defaultDao;


    @Override
    public Page<BlogsListEntity> queryAll(BlogsListVO querySearch) {
        return listDao.findAll(getBlogsListSpecification(querySearch), PageRequest.of(querySearch.getCurrent() - 1, querySearch.getPageSize()));
    }

    @Override
    public List<BlogsListVO> queryAllTitle(List<BlogsListEntity> content) {
        //??????????????????
        Map<BlogType, List<BlogsListEntity>> collect = content.stream().collect(Collectors.groupingBy(BlogsListEntity::getBlogtype));
        //??????
        List<String> sayingIds = collect.get(BlogType.SAYING).stream().map(BlogsListEntity::getBlogid).collect(Collectors.toList());
        Map<String, String> sayingMap = sayingDao.querySayingByIds(sayingIds).stream()
                .collect(Collectors.toMap(item->item.get("id"),item->item.get("saying")));
        //??????
        List<String> linnkIds = collect.get(BlogType.LINK).stream()
                .map(BlogsListEntity::getBlogid)
                .collect(Collectors.toList());
        Map<String, String> linkMap = linkDao.querySketchByIds(linnkIds).stream()
                .collect(Collectors.toMap(item->item.get("id"),item->item.get("sketch")));
        //??????
        List<BlogsListEntity> defaultEntitys = collect.getOrDefault(BlogType.IMAGE,new ArrayList<>());
        defaultEntitys.addAll(collect.getOrDefault(BlogType.IMAGES,new ArrayList<>()));
        defaultEntitys.addAll(collect.getOrDefault(BlogType.VIDEO,new ArrayList<>()));
        defaultEntitys.addAll(collect.getOrDefault(BlogType.AUDIO,new ArrayList<>()));
        List<String> defaultIds = defaultEntitys.stream()
                .map(BlogsListEntity::getBlogid).collect(Collectors.toList());
        Map<String, String> defaultMap = defaultDao.queryTitleByIds(defaultIds).stream()
                .collect(Collectors.toMap(item->item.get("id"),item->item.get("title")));


        return content.stream().map(item -> {
            BlogsListVO vo = new BlogsListVO();
            BeanUtils.copyProperties(item, vo);
            vo.setTitle(sayingMap.getOrDefault(item.getBlogid(),linkMap.getOrDefault(item.getBlogid(),defaultMap.getOrDefault(item.getBlogid(),""))));
            return vo;
        }).collect(Collectors.toList());
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
        Object back = null;
        switch (type){
            case SAYING:
                Optional<BlogsSayingEntity> byId = sayingDao.findById(id);
                if (byId.isPresent()){
                    BlogsSayingEntity entity = byId.get();
                    BlogsSayingVO vo = new BlogsSayingVO();
                    BeanUtils.copyProperties(entity,vo);
                    back = vo;
                }
                break;
            case LINK:
                Optional<BlogsLinkEntity> byId1 = linkDao.findById(id);
                if (byId1.isPresent()){
                    BlogsLinkEntity entity = byId1.get();
                    BlogsLinkVO vo = new BlogsLinkVO();
                    BeanUtils.copyProperties(entity,vo);
                    back = vo;
                }
                break;
            case IMAGE:
            case IMAGES:
            case VIDEO:
            case AUDIO:
                Optional<BlogsDefaultEntity> byId2 = defaultDao.findById(id);
                if (byId2.isPresent()){
                    BlogsDefaultEntity entity = byId2.get();
                    BlogsDefaultVO vo = new BlogsDefaultVO();
                    BeanUtils.copyProperties(entity,vo);
                    ClassificationModel classificationModel = new ClassificationModel();
                    BeanUtils.copyProperties(entity.getClassify(),classificationModel);
                    vo.setClassify(classificationModel);
                    List<TagLibraryModel> tagvos = entity.getTags().stream().map(tag -> {
                        TagLibraryModel tagLibraryModel = new TagLibraryModel();
                        BeanUtils.copyProperties(tag, tagLibraryModel);
                        return tagLibraryModel;
                    }).collect(Collectors.toList());
                    vo.setTags(tagvos);
                    List<BlogsDefaultImagesVO> images = entity.getImages().stream().map(image -> {
                        BlogsDefaultImagesVO imagesVO = new BlogsDefaultImagesVO();
                        BeanUtils.copyProperties(image, imagesVO);
                        return imagesVO;
                    }).collect(Collectors.toList());
                    vo.setImages(images);
                    back = vo;
                }
                break;
            default:
                throw new Exception("?????????????????????!");
        }
        return back;
    }

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
}
