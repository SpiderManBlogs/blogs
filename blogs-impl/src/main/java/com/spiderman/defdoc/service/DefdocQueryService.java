package com.spiderman.defdoc.service;

import com.spiderman.defdoc.vo.DefdocListVO;
import com.spiderman.defdoc.vo.DefdocVO;

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
}
