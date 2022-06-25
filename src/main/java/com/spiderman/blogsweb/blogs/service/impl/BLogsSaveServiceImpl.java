package com.spiderman.blogsweb.blogs.service.impl;

import com.spiderman.blogsweb.blogs.converter.BlogType;
import com.spiderman.blogsweb.blogs.entity.*;
import com.spiderman.blogsweb.blogs.repository.BlogsDefaultRepository;
import com.spiderman.blogsweb.blogs.repository.BlogsLinkRepository;
import com.spiderman.blogsweb.blogs.repository.BlogsListRepository;
import com.spiderman.blogsweb.blogs.repository.BlogsSayingRepository;
import com.spiderman.blogsweb.blogs.service.BlogsSaveService;
import com.spiderman.blogsweb.blogs.vo.BlogsDefaultVO;
import com.spiderman.blogsweb.blogs.vo.BlogsLinkVO;
import com.spiderman.blogsweb.blogs.vo.BlogsSayingVO;
import com.spiderman.blogsweb.classification.entity.ClassificationEntity;
import com.spiderman.blogsweb.classification.model.ClassificationModel;
import com.spiderman.blogsweb.serial.entity.SerialNumberEntity;
import com.spiderman.blogsweb.serial.repository.SerialRepository;
import com.spiderman.blogsweb.tag.entity.TagLibraryEntity;
import com.spiderman.blogsweb.tag.repository.TagLibraryRepository;
import com.spiderman.blogsweb.utils.CheckoutException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BLogsSaveServiceImpl implements BlogsSaveService {

    private static final Logger log = LogManager.getLogger(BLogsSaveServiceImpl.class.getName());

    @Value("${blogs.create}")
    private String create;

    private SerialRepository serialDao;
    private BlogsSayingRepository sayingDao;
    private BlogsLinkRepository linkDao;
    private BlogsListRepository listDao;
    private BlogsDefaultRepository defaultDao;
    private TagLibraryRepository tagDao;

    @Override
    public BlogsSayingVO addSaying(BlogsSayingVO blogsSayingVO) {
        BlogsSayingEntity entity = new BlogsSayingEntity();
        BeanUtils.copyProperties(blogsSayingVO,entity);
        entity.setCreator(create);
        entity.setCode(this.initCode(BlogType.SAYING));
        entity.setBlogtype(BlogType.SAYING);
        BlogsSayingEntity save = sayingDao.save(entity);
        this.addList(save.getId(),BlogType.SAYING);
        BlogsSayingVO back = new BlogsSayingVO();
        BeanUtils.copyProperties(save,back);
        return back;
    }

    @Override
    public BlogsSayingVO updateSaying(BlogsSayingVO blogsSayingVO) throws CheckoutException {
        Optional<BlogsSayingEntity> byId = sayingDao.findById(blogsSayingVO.getId());
        if (!byId.isPresent()){
            throw new CheckoutException("查询数据不存在。");
        }
        BlogsSayingEntity entity = byId.get();
        entity.setSaying(blogsSayingVO.getSaying());
        entity.setProvenance(blogsSayingVO.getProvenance());
        BlogsSayingEntity save = sayingDao.save(entity);
        BlogsSayingVO back = new BlogsSayingVO();
        BeanUtils.copyProperties(save,back);
        return back;
    }

    @Override
    public BlogsLinkVO addLink(BlogsLinkVO blogsLinkVO) {
        BlogsLinkEntity entity = new BlogsLinkEntity();
        BeanUtils.copyProperties(blogsLinkVO,entity);
        entity.setCreator(create);
        entity.setCode(this.initCode(BlogType.LINK));
        entity.setBlogtype(BlogType.LINK);
        BlogsLinkEntity save = linkDao.save(entity);
        this.addList(save.getId(),BlogType.LINK);
        BlogsLinkVO back = new BlogsLinkVO();
        BeanUtils.copyProperties(save,back);
        return back;
    }

    @Override
    public BlogsLinkVO updateLink(BlogsLinkVO blogsLinkVO) throws CheckoutException {
        Optional<BlogsLinkEntity> byId = linkDao.findById(blogsLinkVO.getId());
        if (!byId.isPresent()){
            throw new CheckoutException("查询数据不存在。");
        }
        BlogsLinkEntity entity = byId.get();
        entity.setSketch(blogsLinkVO.getSketch());
        entity.setUrl(blogsLinkVO.getUrl());
        BlogsLinkEntity save = linkDao.save(entity);
        BlogsLinkVO back = new BlogsLinkVO();
        BeanUtils.copyProperties(save,back);
        return back;
    }

    @Override
    public BlogsDefaultVO addDefault(BlogsDefaultVO blogsDefaultVO) {
        BlogsDefaultEntity entity = new BlogsDefaultEntity();
        BlogsDefaultCopy(blogsDefaultVO,entity);
        //处理标签
        List<TagLibraryEntity> tags = entity.getTags();
        List<String> tagNames = tags.stream().map(TagLibraryEntity::getName).collect(Collectors.toList());
        List<TagLibraryEntity> tagentitys = tagDao.queryByNames(tagNames);
        Map<String, TagLibraryEntity> tagNameMap = tagentitys.stream().collect(Collectors.toMap(TagLibraryEntity::getName, item -> item));
        tags = tags.stream().map(tag -> tagNameMap.getOrDefault(tag.getName(), tag)).collect(Collectors.toList());
        entity.setTags(tags);
        //处理标签结束
        BlogsDefaultEntity save = defaultDao.save(entity);
        //保存列表
        this.addList(save.getId(),blogsDefaultVO.getBlogtype());
        //保存当前博客id 到 上一篇博客的下一篇字段上
        defaultDao.setNext(save.getId());
        BlogsDefaultVO back = new BlogsDefaultVO();
        BeanUtils.copyProperties(save,back);
        return back;
    }

    private void BlogsDefaultCopy(BlogsDefaultVO vo,BlogsDefaultEntity entity){
        BeanUtils.copyProperties(vo,entity);
        entity.setCreator(create);
        entity.setCode(this.initCode(vo.getBlogtype()));
        //封面图片子表
        List<BlogsDefaultImagesEntity> imagesEntity = vo.getImages().stream().map(image -> {
            BlogsDefaultImagesEntity imageEntity = new BlogsDefaultImagesEntity();
            BeanUtils.copyProperties(image,imageEntity);
            imageEntity.setCode(this.initCode(null));
            return imageEntity;
        }).collect(Collectors.toList());
        entity.setImages(imagesEntity);
        //分类
        ClassificationModel classify = vo.getClassify();
        ClassificationEntity classificationEntity = new ClassificationEntity();
        BeanUtils.copyProperties(classify,classificationEntity);
        entity.setClassify(classificationEntity);
        //标签
        List<TagLibraryEntity> tagLibraryEntities = vo.getTags().stream().map(tag -> {
            TagLibraryEntity tagLibraryEntity = new TagLibraryEntity();
            BeanUtils.copyProperties(tag, tagLibraryEntity);
            tagLibraryEntity.setCode(this.initCode(null));
            return tagLibraryEntity;
        }).collect(Collectors.toList());
        entity.setTags(tagLibraryEntities);
    }

    @Override
    public BlogsDefaultVO updateDefault(BlogsDefaultVO blogsDefaultVO) throws CheckoutException {
        Optional<BlogsDefaultEntity> byId = defaultDao.findById(blogsDefaultVO.getId());
        if (!byId.isPresent()){
            throw new CheckoutException("查询数据不存在。");
        }
        BlogsDefaultEntity entity = byId.get();
        entity.setTitle(blogsDefaultVO.getTitle());
        BlogsDefaultEntity save = defaultDao.save(entity);
        BlogsDefaultVO back = new BlogsDefaultVO();
        BeanUtils.copyProperties(save,back);
        return back;
    }

    /**
     * 添加列表数据
     * @param blogid 博客id
     * @param blogType 博客类别
     * @return 列表保存后数据
     */
    private BlogsListEntity addList(String blogid,BlogType blogType){
        BlogsListEntity entity = new BlogsListEntity();
        entity.setBlogid(blogid);
        entity.setBlogtype(blogType);
        entity.setCode(this.initCode(BlogType.LIST));
        Long order = listDao.getMaxOrder();
        entity.setListorder(order != null ? order+1 : 1);
        return listDao.save(entity);
    }

    /**
     * 编码初始化类
     * 生成规则：blogType.name + 'yyyy-MM-dd' + 4位流水
     * @param blogType 类型
     * @return 编码
     */
    private String initCode(BlogType blogType){
        Calendar calendar = Calendar.getInstance();
        String date = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
        List<SerialNumberEntity> serialNumberEntities = serialDao.queryByDate(date);
        SerialNumberEntity serialNumberEntity;
        if (serialNumberEntities.size() == 0){
            serialNumberEntity = new SerialNumberEntity();
            serialNumberEntity.setDate(date);
            serialNumberEntity.setNum(1);
            serialDao.save(serialNumberEntity);
        }else {
            serialNumberEntity = serialNumberEntities.get(0);
            serialDao.updateNumByDate(serialNumberEntity.getId());
            serialNumberEntity.setNum(serialNumberEntity.getNum()+1);
        }
        DecimalFormat df2 = new DecimalFormat("0000");
        return (blogType != null ? blogType.name() : "") + date + df2.format(serialNumberEntity.getNum());
    }



    @Autowired
    public void setTagDao(TagLibraryRepository tagDao) {
        this.tagDao = tagDao;
    }

    @Autowired
    public void setDefaultDao(BlogsDefaultRepository defaultDao) {
        this.defaultDao = defaultDao;
    }

    @Autowired
    public void setLinkDao(BlogsLinkRepository linkDao) {
        this.linkDao = linkDao;
    }

    @Autowired
    public void setListDao(BlogsListRepository listDao) {
        this.listDao = listDao;
    }

    @Autowired
    public void setSayingDao(BlogsSayingRepository sayingDao) {
        this.sayingDao = sayingDao;
    }

    @Autowired
    public void setSerialDao(SerialRepository serialDao) {
        this.serialDao = serialDao;
    }
}
