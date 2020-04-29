package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.*;
import club.banyuan.zgMallMgt.dao.entity.*;
import club.banyuan.zgMallMgt.dto.CreateProductReq;
import club.banyuan.zgMallMgt.dto.PmsProductInfoResp;
import club.banyuan.zgMallMgt.dto.PmsProductResp;
import club.banyuan.zgMallMgt.service.OssFileService;
import club.banyuan.zgMallMgt.service.PmsProductService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.PMS_PRODUCT_NOT_EXIST;

@Service
public class PmsProductServiceImpl implements PmsProductService {


    @Autowired
    private PmsProductDao pmsProductDao;
    @Autowired
    private SmsFlashPromotionProductRelationDao smsFlashPromotionProductRelationDao;
    @Autowired
    private PmsMemberPriceDao pmsMemberPriceDao;
    @Autowired
    private PmsProductFullReductionDao pmsProductFullReductionDao;
    @Autowired
    private PmsProductLadderDao pmsProductLadderDao;
    @Autowired
    private PmsProductAttributeValueDao pmsProductAttributeValueDao;
    @Autowired
    private PmsSkuStockDao pmsSkuStockDao;
    @Autowired
    private CmsSubjectProductRelationDao cmsSubjectProductRelationDao;
    @Autowired
    private CmsPrefrenceAreaProductRelationDao cmsPrefrenceAreaProductRelationDao;
    @Autowired
    private OssFileService ossFileService;


    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize, String keyword, String productSn, Long productCategoryId, Long brandId) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductExample pmsProductExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = pmsProductExample.createCriteria();
        if (StrUtil.isNotBlank(keyword)) {
            criteria.andNameLike(StrUtil.concat(true, "%", keyword, "%"));
        }
        if (StrUtil.isNotBlank(productSn)) {
            criteria.andProductSnEqualTo(StrUtil.concat(true, "%", productSn, "%"));
        }
        if (productCategoryId != null) {
            criteria.andProductCategoryIdEqualTo(productCategoryId);
        }
        if (brandId != null) {
            criteria.andBrandIdEqualTo(brandId);
        }
        List<PmsProduct> pmsProducts = pmsProductDao.selectByExample(pmsProductExample);
//        List<PmsProduct> pmsProducts = pmsProductDao.selectAll();
        PageInfo<PmsProduct> pageInfo = new PageInfo<>(pmsProducts);
        List<PmsProductResp> collect = pmsProducts.stream().map(t -> {
            PmsProductResp pmsProductResp = new PmsProductResp();
            BeanUtil.copyProperties(t, pmsProductResp);
            return pmsProductResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo, collect);
    }


    @Override
    @Transactional
    public Integer create(CreateProductReq createProductReq) {
        PmsProduct pmsProduct = createProductReq.findPmsProduct();
        SmsFlashPromotionProductRelation smsFlashPromotionProductRelation = createProductReq.findSmsFlashPromotionProductRelation();
        List<PmsMemberPrice> pmsMemberPrices = createProductReq.getMemberPriceList();
        List<PmsProductFullReduction> pmsProductFullReductions = createProductReq.getProductFullReductionList();
        List<PmsProductLadder> pmsProductLadders = createProductReq.getProductLadderList();
        List<PmsProductAttributeValue> pmsProductAttributeValues = createProductReq.getProductAttributeValueList();
        List<PmsSkuStock> pmsSkuStocks = createProductReq.getSkuStockList();
        List<CmsSubjectProductRelation> cmsSubjectProductRelations = createProductReq.getSubjectProductRelationList();
        List<CmsPrefrenceAreaProductRelation> cmsPrefrenceAreaProductRelations = createProductReq.getPrefrenceAreaProductRelationList();
        pmsProductDao.insert(pmsProduct);
        Long productId = pmsProduct.getId();
        smsFlashPromotionProductRelation.setProductId(productId);
        smsFlashPromotionProductRelationDao.insert(smsFlashPromotionProductRelation);

        if (pmsMemberPrices.size() > 0) {
            pmsMemberPrices.forEach(t -> {
                t.setProductId(productId);
            });
            pmsMemberPriceDao.insertMany(pmsMemberPrices);
        }
        if (pmsProductFullReductions.size() > 0) {
            pmsProductFullReductions.forEach(t -> {
                t.setProductId(productId);
            });
            pmsProductFullReductionDao.insertMany(pmsProductFullReductions);
        }


        if (pmsProductLadders.size() > 0) {
            pmsProductLadders.forEach(t -> {
                t.setProductId(productId);
            });
            pmsProductLadderDao.insertMany(pmsProductLadders);
        }

        if (pmsProductAttributeValues.size() > 0) {
            pmsProductAttributeValues.forEach(t -> {
                t.setProductId(productId);
            });
            pmsProductAttributeValueDao.insertMany(pmsProductAttributeValues);
        }

        if (pmsSkuStocks.size() > 0) {
            pmsSkuStocks.forEach(t -> {
                t.setProductId(productId);
            });
            pmsSkuStockDao.insertMany(pmsSkuStocks);
        }
        if (cmsSubjectProductRelations.size() > 0) {
            cmsSubjectProductRelations.forEach(t -> {
                t.setProductId(productId);
            });
            cmsSubjectProductRelationDao.insertMany(cmsSubjectProductRelations);
        }
        if (cmsPrefrenceAreaProductRelations.size() > 0) {
            cmsPrefrenceAreaProductRelations.forEach(t -> {
                t.setProductId(productId);
            });
            cmsPrefrenceAreaProductRelationDao.insertMany(cmsPrefrenceAreaProductRelations);
        }
        return 1;
    }

    @Override
    @Transactional
    public Integer delete(List<Long> ids, Integer deleteStatus) {
        List<PmsProduct> pmsProducts = pmsProductDao.selectByPrimaryKeys(ids);
        pmsProducts.forEach(t -> {
            if (ObjectUtil.isEmpty(t)) {
                throw new ReqFailException(PMS_PRODUCT_NOT_EXIST);
            }
        });

        pmsProductDao.deleteByPrimaryKeys(ids);
        smsFlashPromotionProductRelationDao.deleteByProductIds(ids);
        pmsMemberPriceDao.deleteByProductIds(ids);
        pmsProductFullReductionDao.deleteByProductIds(ids);
        pmsProductLadderDao.deleteByProductIds(ids);
        pmsProductAttributeValueDao.deleteByProductIds(ids);
        pmsSkuStockDao.deleteByProductIds(ids);
        cmsSubjectProductRelationDao.deleteByProductIds(ids);
        cmsPrefrenceAreaProductRelationDao.deleteByProductIds(ids);

        pmsProducts.forEach(t -> {
            String pic = t.getPic();
            if (StrUtil.isNotBlank(pic)) {
                try {
                    ossFileService.delete(pic);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String albumPics = t.getAlbumPics();
            if (StrUtil.isNotBlank(albumPics)) {
                String[] split = albumPics.split(",");
                Arrays.stream(split).forEach(s -> {
                    try {
                        ossFileService.delete(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });
            }
        });

        return deleteStatus;
    }

    @Override
    public PmsProductInfoResp updateInfo(Long productId) {
        PmsProduct pmsProduct = pmsProductDao.selectByPrimaryKey(productId);
        if (ObjectUtil.isEmpty(pmsProduct)) {
            throw new ReqFailException(PMS_PRODUCT_NOT_EXIST);
        }

        List<SmsFlashPromotionProductRelation> smsFlashPromotionProductRelations = smsFlashPromotionProductRelationDao.selectByProductId(productId);
        List<PmsMemberPrice> pmsMemberPrices = pmsMemberPriceDao.selectByProductId(productId);
        List<PmsProductFullReduction> pmsProductFullReductions = pmsProductFullReductionDao.selectByProductId(productId);
        List<PmsProductLadder> pmsProductLadders = pmsProductLadderDao.selectByProductId(productId);
        List<PmsProductAttributeValue> pmsProductAttributeValues = pmsProductAttributeValueDao.selectByProductId(productId);
        List<PmsSkuStock> pmsSkuStocks = pmsSkuStockDao.selectByProductId(productId);
        List<CmsSubjectProductRelation> cmsSubjectProductRelations = cmsSubjectProductRelationDao.selectByProductId(productId);
        List<CmsPrefrenceAreaProductRelation> cmsPrefrenceAreaProductRelations = cmsPrefrenceAreaProductRelationDao.selectByProductId(productId);
        PmsProductInfoResp pmsProductInfoResp = new PmsProductInfoResp();
        pmsProductInfoResp.setPmsProduct(pmsProduct);
        pmsProductInfoResp.setSmsFlashPromotionProductRelation(smsFlashPromotionProductRelations.get(0));
        pmsProductInfoResp.setMemberPriceList(pmsMemberPrices);
        pmsProductInfoResp.setProductFullReductionList(pmsProductFullReductions);
        pmsProductInfoResp.setProductLadderList(pmsProductLadders);
        pmsProductInfoResp.setProductAttributeValueList(pmsProductAttributeValues);
        pmsProductInfoResp.setSkuStockList(pmsSkuStocks);
        pmsProductInfoResp.setSubjectProductRelationList(cmsSubjectProductRelations);
        pmsProductInfoResp.setPrefrenceAreaProductRelationList(cmsPrefrenceAreaProductRelations);
        return pmsProductInfoResp;
    }

    @Override
    @Transactional
    public Integer update(CreateProductReq createProductReq, Long productId) {
        PmsProduct pmsProduct = pmsProductDao.selectByPrimaryKey(productId);
        if (ObjectUtil.isEmpty(pmsProduct)) {
            throw new ReqFailException(PMS_PRODUCT_NOT_EXIST);
        }

        SmsFlashPromotionProductRelation smsFlashPromotionProductRelation = createProductReq.findSmsFlashPromotionProductRelation();
        List<PmsMemberPrice> pmsMemberPrices = createProductReq.getMemberPriceList();
        List<PmsProductFullReduction> pmsProductFullReductions = createProductReq.getProductFullReductionList();
        List<PmsProductLadder> pmsProductLadders = createProductReq.getProductLadderList();
        List<PmsProductAttributeValue> pmsProductAttributeValues = createProductReq.getProductAttributeValueList();
        List<PmsSkuStock> pmsSkuStocks = createProductReq.getSkuStockList();
        List<CmsSubjectProductRelation> cmsSubjectProductRelations = createProductReq.getSubjectProductRelationList();
        List<CmsPrefrenceAreaProductRelation> cmsPrefrenceAreaProductRelations = createProductReq.getPrefrenceAreaProductRelationList();

        PmsProduct pmsProduct1 = createProductReq.findPmsProduct();
        pmsProduct1.setId(productId);
        pmsProductDao.updateByPrimaryKey(pmsProduct1);

        smsFlashPromotionProductRelationDao.deleteByProductId(productId);
        smsFlashPromotionProductRelation.setProductId(productId);
        smsFlashPromotionProductRelationDao.insert(smsFlashPromotionProductRelation);

        pmsMemberPriceDao.deleteByProductId(productId);
        if (pmsMemberPrices.size() > 0) {
            pmsMemberPrices.forEach(t -> {
                t.setProductId(productId);
            });
            pmsMemberPriceDao.insertMany(pmsMemberPrices);
        }

        pmsProductFullReductionDao.deleteByProductId(productId);
        if (pmsProductFullReductions.size() > 0) {
            pmsProductFullReductions.forEach(t -> {
                t.setProductId(productId);
            });
            pmsProductFullReductionDao.insertMany(pmsProductFullReductions);
        }

        pmsProductLadderDao.deleteByProductId(productId);
        if (pmsProductLadders.size() > 0) {
            pmsProductLadders.forEach(t -> {
                t.setProductId(productId);
            });
            pmsProductLadderDao.insertMany(pmsProductLadders);
        }

        pmsProductAttributeValueDao.deleteByProductId(productId);
        if (pmsProductAttributeValues.size() > 0) {
            pmsProductAttributeValues.forEach(t -> {
                t.setProductId(productId);
            });
            pmsProductAttributeValueDao.insertMany(pmsProductAttributeValues);
        }

        pmsSkuStockDao.deleteByProductId(productId);
        if (pmsSkuStocks.size() > 0) {
            pmsSkuStocks.forEach(t -> {
                t.setProductId(productId);
            });
            pmsSkuStockDao.insertMany(pmsSkuStocks);
        }

        int i = cmsSubjectProductRelationDao.deleteByProductId(productId);
        if (cmsSubjectProductRelations.size() > 0) {
            cmsSubjectProductRelations.forEach(t -> {
                t.setProductId(productId);
            });
            cmsSubjectProductRelationDao.insertMany(cmsSubjectProductRelations);
        }

        cmsPrefrenceAreaProductRelationDao.deleteByProductId(productId);
        if (cmsPrefrenceAreaProductRelations.size() > 0) {
            cmsPrefrenceAreaProductRelations.forEach(t -> {
                t.setProductId(productId);
            });
            cmsPrefrenceAreaProductRelationDao.insertMany(cmsPrefrenceAreaProductRelations);
        }


        String pic1 = pmsProduct1.getPic();
        String pic = pmsProduct.getPic();

        if (StrUtil.isNotBlank(pic)) {
            if (!pic.equals(pic1)) {
                try {
                    ossFileService.delete(pic);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String albumPics = pmsProduct.getAlbumPics();
        String albumPics1 = pmsProduct1.getAlbumPics();
        if (StrUtil.isNotBlank(albumPics) && StrUtil.isNotBlank(albumPics1)) {
            String[] split = albumPics.split(",");
            String[] split1 = albumPics1.split(",");
            Arrays.stream(split).forEach(t -> {
                List<String> collect = Arrays.stream(split1).collect(Collectors.toList());
                if (!collect.contains(t)) {
                    try {
                        ossFileService.delete(t);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (StrUtil.isNotBlank(albumPics) && StrUtil.isBlank(albumPics1)) {
            String[] split = albumPics.split(",");
            Arrays.stream(split).forEach(t -> {
                try {
                    ossFileService.delete(t);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return i;
    }

    @Override
    public Integer publishStatus(List<Long> ids, Integer publishStatus) {
        return pmsProductDao.updatePublishStatusByIds(ids, publishStatus);
    }

    @Override
    public Integer recommendStatus(List<Long> ids, Integer recommendStatus) {
        return pmsProductDao.updateRecommendStatusByIds(ids, recommendStatus);
    }

    @Override
    public Integer newStatus(List<Long> ids, Integer newStatus) {
        return pmsProductDao.updateNewStatusByIds(ids, newStatus);
    }

}
