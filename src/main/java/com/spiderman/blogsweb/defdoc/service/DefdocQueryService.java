package com.spiderman.blogsweb.defdoc.service;


import com.spiderman.blogsweb.defdoc.vo.DefdocListVO;
import com.spiderman.blogsweb.defdoc.vo.DefdocVO;

import java.util.List;

public interface DefdocQueryService {

    /**
     * 查询档案集合
     * @return 档案集合
     */
    List<DefdocListVO> query();

    /**
     * 查询档案明细
     * @param id 档案listid
     * @return 档案明细
     */
    List<DefdocVO> query(String id);

    /**
     * 详情查询
     * @param id
     * @return
     */
    DefdocVO queryDefDocByid(String id);

    /**
     * 通过list编码查询档案详情
     * @param code list编码
     * @return 档案详情集合
     */
    List<DefdocVO> queryByCode(String code);
}