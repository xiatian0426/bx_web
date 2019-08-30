package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxCase;
import com.acc.model.BxProduct;
import com.acc.model.BxProductImg;
import com.acc.model.BxProductVideo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BxProductMapper {

    List<BxProduct> getProductByPerm(@Param("memberId") String memberId) throws SelectException;

    List<BxProduct> getDetailByProductId(@Param("productId") String productId) throws SelectException;

    BxProduct getProductById(int id) throws SelectException;

    void deleteProImgById(@Param("id") String id) throws Exception;

    void deleteProVideoById(@Param("id") String id) throws Exception;

    void deleteProImgByProId(@Param("productId") String productId) throws Exception;

    void deleteProVideoByProId(@Param("productId") String productId) throws Exception;

    void deleteProByProId(@Param("id") String id) throws Exception;

    void updateProImgByProId(BxProductImg bxProductImg) throws Exception;

    void updateProVideoByProId(BxProduct bxProduct) throws Exception;

    void updateProByProId(BxProduct bxProduct) throws Exception;

    void insertProductImg(BxProductImg bxProductImg) throws Exception;

    void insertProductVideo(BxProductVideo bxProductVideo) throws Exception;

    void insertProduct(BxProduct bxProduct) throws Exception;

    List<BxProduct> getProDetail(@Param("productId") String productId) throws SelectException;

    BxProductImg getProductDetailImgById(String id) throws SelectException;

    List<BxProductImg> getProductDetailImgByProId(@Param("productId") String productId) throws SelectException;

    BxProductVideo getProductDetailVideoById(String id) throws SelectException;
}
