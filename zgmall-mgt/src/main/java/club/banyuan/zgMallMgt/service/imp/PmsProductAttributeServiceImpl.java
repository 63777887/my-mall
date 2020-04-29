package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.PmsProductAttributeDao;
import club.banyuan.zgMallMgt.dao.PmsProductCategoryAttributeRelationDao;
import club.banyuan.zgMallMgt.dao.entity.PmsProductAttribute;
import club.banyuan.zgMallMgt.dto.AttrInfoResp;
import club.banyuan.zgMallMgt.dto.PmsProductAttributeResp;
import club.banyuan.zgMallMgt.service.PmsProductAttributeService;
import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.PMS_PRODUCT_ATTRIBUTE_EMPTY;
import static club.banyuan.zgMallMgt.common.FailReason.PMS_PRODUCT_ATTRIBUTE_NOT_EXIST;

@Service
public class
PmsProductAttributeServiceImpl implements PmsProductAttributeService {

    @Autowired
    private PmsProductAttributeDao pmsProductAttributeDao;
    @Autowired
    private PmsProductCategoryAttributeRelationDao pmsProductCategoryAttributeRelationDao;

    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize, Integer type, Long productAttributeCategoryId) {
        PageHelper.startPage(pageNum, pageSize);
        List<PmsProductAttribute> pmsProductAttributes = pmsProductAttributeDao.selectByProductAttributeCategoryIdAndType(productAttributeCategoryId,type);
        PageInfo<PmsProductAttribute> pageInfo = new PageInfo<>(pmsProductAttributes);
        List<PmsProductAttributeResp> collect = pmsProductAttributes.stream().map(t -> {
            PmsProductAttributeResp pmsProductAttributeResp = new PmsProductAttributeResp();
            BeanUtil.copyProperties(t, pmsProductAttributeResp);
            return pmsProductAttributeResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo,collect);
    }

    @Override
    public List<AttrInfoResp> attrInfo(Long productCategoryId) {
       return pmsProductCategoryAttributeRelationDao.selectByProductCategoryId(productCategoryId);

    }

    @Override
    public Integer create(PmsProductAttribute pmsProductAttribute) {
        if (BeanUtil.isEmpty(pmsProductAttribute)){
            throw new ReqFailException(PMS_PRODUCT_ATTRIBUTE_EMPTY);
        }
        return pmsProductAttributeDao.insert(pmsProductAttribute);
    }

    @Override
    public PmsProductAttributeResp showAttr(Long id) {
        PmsProductAttribute pmsProductAttribute = pmsProductAttributeDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(pmsProductAttribute)){
            throw new ReqFailException(PMS_PRODUCT_ATTRIBUTE_NOT_EXIST);
        }
        PmsProductAttributeResp pmsProductAttributeResp = new PmsProductAttributeResp();
        BeanUtil.copyProperties(pmsProductAttribute, pmsProductAttributeResp);
        return pmsProductAttributeResp;
    }

    @Override
    public Integer update(Long id, PmsProductAttribute pmsProductAttribute) {
        PmsProductAttribute pmsProductAttribute1 = pmsProductAttributeDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(pmsProductAttribute1)){
            throw new ReqFailException(PMS_PRODUCT_ATTRIBUTE_NOT_EXIST);
        }
        pmsProductAttribute.setId(id);
        return pmsProductAttributeDao.updateByPrimaryKey(pmsProductAttribute);
    }

    @Override
    public Integer delete(List<Long> ids) {
        return pmsProductAttributeDao.deleteByPrimaryKeys(ids);
    }

}
