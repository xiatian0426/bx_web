package com.acc.service;

import com.acc.exception.SelectException;
import com.acc.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IBxProductService {
	/**
	 * 根据id获取会员信息
	 * @return
	 * @throws SelectException
	 */
	BxMember getMemberByWechat(String wechat) throws SelectException;

    /**
     * 根据微信号获取对应权限产品
     * @return
     * @throws SelectException
     */
    List<BxProduct> getProductByPerm(String memberId) throws SelectException;
    /**
     * 根据id获取产品
     * @return
     * @throws SelectException
     */
    BxProduct getProductById(int id) throws SelectException;
    /**
     * 根据产品id获取产品详情
     * @return
     * @throws SelectException
     */
    List<BxProduct> getDetailByProductId(String productId) throws SelectException;
    /**
     * 获取案例产品详情01
     * @return
     * @throws SelectException
     */
    BxCase getCaseDetail(String productId) throws SelectException;

    void deleteById(String productId) throws Exception;

    List<BxProduct> getProDetail(String productId) throws SelectException;

    void updateProduct(BxProduct bxProduct) throws Exception;

    void addProduct(BxProduct bxProduct) throws Exception;

    void insertProductVideo(BxProductVideo bxProductVideo) throws Exception;

    void insertProductImg(BxProductImg bxProductImg) throws Exception;

    BxProductImg getProductDetailImgById(String id) throws SelectException;

    BxProductVideo getProductDetailVideoById(String id) throws SelectException;

    void deleteProductDetailImgByProId(String productId) throws Exception;

    void deleteProductDetailVideoById(String id) throws Exception;

    List<BxProductImg> getProductDetailImgByProId(@Param("productId") String productId) throws SelectException;
}
