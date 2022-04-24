package com.spiderman.blogsweb.defdoc.service;


import com.spiderman.blogsweb.defdoc.vo.DefdocListVO;
import com.spiderman.blogsweb.defdoc.vo.DefdocVO;
import com.spiderman.blogsweb.utils.CheckoutException;
import com.spiderman.blogsweb.utils.QueryNullException;

public interface DefdocService {

    /**
     * 档案新增
     * @param defdoc 档案vo
     * @return 保存后结果
     */
    DefdocListVO add(DefdocListVO defdoc) throws CheckoutException;

    /**
     * 档案明细新增
     * @param defdoc 档案明细
     * @return 保存后明细
     */
    DefdocVO add(DefdocVO defdoc) throws CheckoutException;

    /**
     * 删除档案
     * @param defdocList 档案vo
     */
    void delete(DefdocListVO defdocList) throws QueryNullException;

    /**
     * 删除档案明细
     * @param defdoc 档案明细vo
     */
    void delete(DefdocVO defdoc) throws QueryNullException;

    /**
     * 修改档案
     * @param defdocList 档案vo
     * @return 修改后的结果
     */
    DefdocListVO edit(DefdocListVO defdocList) throws QueryNullException;

    /**
     * 修改档案明细
     * @param defdoc 档案明细vo
     * @return 修改后的结果
     */
    DefdocVO edit(DefdocVO defdoc) throws QueryNullException;
}
