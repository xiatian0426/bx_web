package com.acc.service.impl;

import com.acc.dao.BxCaseMapper;
import com.acc.dao.BxMemberMapper;
import com.acc.dao.BxProductMapper;
import com.acc.exception.SelectException;
import com.acc.model.*;
import com.acc.service.IBxProductService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("bxProductService")
@Transactional
public class BxProductServiceImpl extends BaseServiceImpl<BxProduct> implements IBxProductService {

	private static Logger _logger = LoggerFactory.getLogger(BxProductServiceImpl.class);
	@Autowired
	private BxMemberMapper bxMemberMapper;
    @Autowired
    private BxProductMapper bxProductMapper;
    @Autowired
    private BxCaseMapper bxCaseMapper;

    @Override
	public BxMember getMemberByWechat(String wechat) throws SelectException {
		return bxMemberMapper.getMemberByWechat(wechat);
	}

    @Override
    public List<BxProduct> getProductByPerm(String memberId) throws SelectException {
        return bxProductMapper.getProductByPerm(memberId);
    }

    @Override
    public BxProduct getProductById(int id) throws SelectException {
        return bxProductMapper.getProductById(id);
    }

    @Override
    public List<BxProduct> getDetailByProductId(String productId) throws SelectException {
        return bxProductMapper.getDetailByProductId(productId);
    }

    @Override
    public BxCase getCaseDetail(String productId ) throws SelectException {
        return bxCaseMapper.getCaseDetail(productId);
    }

    @Override
    public void deleteById(String productId) throws Exception {
        //删除产品案例
        bxCaseMapper.deleteCaseByProId(productId);
        //删除产品详情图片
        bxProductMapper.deleteProImgByProId(productId);
        //删除产品详情视频
        bxProductMapper.deleteProVideoByProId(productId);
        //删除产品
        bxProductMapper.deleteProByProId(productId);
    }

    @Override
    public List<BxProduct> getProDetail(String productId) throws SelectException {
        return bxProductMapper.getProDetail(productId);
    }

    @Override
    public void updateProduct(BxProduct bxProduct) throws Exception {
        bxProductMapper.updateProByProId(bxProduct);
        //更新产品案例
        BxCase bxCase = new BxCase();
        bxCase.setName(bxProduct.getBxCaseName());
        bxCase.setProductName(bxProduct.getBxCaseProductName());
        bxCase.setAge(bxProduct.getBxCaseAge());
        bxCase.setCost(bxProduct.getBxCaseCost());
        bxCase.setTimeLimit(bxProduct.getBxCaseTimeLimit());
        bxCase.setTbContext(bxProduct.getBxCaseTbContext());
        bxCase.setLpContext(bxProduct.getBxCaseLpContext());
        bxCase.setCxContext(bxProduct.getBxCaseCxContext());

        bxCase.setRelaProductId(bxProduct.getId());
        bxCase.setMemberId(bxProduct.getMemberId());
        bxCase.setCreateId(bxProduct.getCreateId());
        bxCaseMapper.deleteCaseByProId(""+bxProduct.getId());
        bxCaseMapper.insertCaseByProId(bxCase);
    }

    @Override
    public void addProduct(BxProduct bxProduct) throws Exception {
        bxProductMapper.insertProduct(bxProduct);
        //更新产品案例
        BxCase bxCase = new BxCase();
        bxCase.setName(bxProduct.getBxCaseName());
        bxCase.setProductName(bxProduct.getBxCaseProductName());
        bxCase.setAge(bxProduct.getBxCaseAge());
        bxCase.setCost(bxProduct.getBxCaseCost());
        bxCase.setTimeLimit(bxProduct.getBxCaseTimeLimit());
        bxCase.setTbContext(bxProduct.getBxCaseTbContext());
        bxCase.setLpContext(bxProduct.getBxCaseLpContext());
        bxCase.setCxContext(bxProduct.getBxCaseCxContext());

        bxCase.setRelaProductId(bxProduct.getId());
        bxCase.setMemberId(bxProduct.getMemberId());
        bxCase.setCreateId(bxProduct.getCreateId());
        bxCaseMapper.insertCaseByProId(bxCase);
    }

    @Override
    public void insertProductVideo(BxProductVideo bxProductVideo) throws Exception {
        bxProductMapper.insertProductVideo(bxProductVideo);
    }

    @Override
    public void insertProductImg(BxProductImg bxProductImg) throws Exception {
        bxProductMapper.insertProductImg(bxProductImg);
    }

    @Override
    public void updateProductImg(BxProductImg bxProductImg) throws Exception {
        bxProductMapper.updateProductImg(bxProductImg);
    }

    @Override
    public BxProductImg getProductDetailImgById(String id) throws SelectException {
        return bxProductMapper.getProductDetailImgById(id);
    }

    @Override
    public BxProductVideo getProductDetailVideoById(String id) throws SelectException {
        return bxProductMapper.getProductDetailVideoById(id);
    }

    @Override
    public void deleteProductDetailImgByProId(String productId) throws Exception {
        bxProductMapper.deleteProImgByProId(productId);
    }

    @Override
    public void deleteProductDetailVideoById(String id) throws Exception {
        bxProductMapper.deleteProVideoById(id);
    }

    @Override
    public List<BxProductImg> getProductDetailImgByProId(@Param("productId") String productId) throws SelectException{
        return bxProductMapper.getProductDetailImgByProId(productId);
    }
}
